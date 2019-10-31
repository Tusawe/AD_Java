# Añadiendo seguridad a la aplicación

Dada la similitud de este sistema con otros sistemas de seguridad de diferentes frameworks y tecnologías, nosotros vamos a recurrir a un filtro para asegurar partes de nuestra Web.

Un filtro nos permite inyectar precondiciones a una petición Web (HTTP GET, POST, PUT, DELETE....) o bien postcondiciones, es decir, antes o después de llamar al servlet que despacha la petición del verbo HTTP, se ejecutaría también el método indicado en el filtro.

Esto nos puede ayudar, por ejemplo, para dar seguridad a nuestras aplicaciones. Si tenemos un formulario que haga POST a un servicio de login, desde el servlet que se encarga del servicio, podríamos crear la sesión o cookie que el filtro después comprobará para ver si estamos *loggeados*.

## El servicio de gestión de sesión

Nuestro servicio consta de un formulario de login que hace un POST al servlet que crea la sesión y manda la cookie al cliente para mantener la sesión.

Un filtro intercepta las peticiones a las URLs protegidas y comprueba que hayamos entrado en el sistema con un usuario válido. Aún no estamos usando ACLs (listas de acceso de usuarios).

Para cerrar la sesión un servlet eliminará la cookie.

### Creando la sesión

**Formulario de login:**  Hace un POST al servlet que comprueba contra la base de datos si existe el usuario con esa contraseña.

```html
<div class="container">
    <div class="jumbotron"><h2>Login</h2></div>
    <div class="form">
        <form action="login" method="POST">
            <input name="username" type="text" placeholder="Nombre de usuario" />
            <input name="pwd" type="password" placeholder="Contraseña" />
            <button type="submit">Enviar</button>
        </form>
    </div>
</div>
```

**Servlet de login**: Recoge los datos del formulario anterior y comprueba contra la base de datos si existe el usuario con esa contraseña.

```java
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Conexion conn = new Conexion();
		try {
			Connection conexion = conn.getConnection();

			// get request parameters for userID and password
			String user = request.getParameter("username");
			String pwd = request.getParameter("pwd");

			String sql = "SELECT * FROM usuario WHERE username=? AND password=?";
			PreparedStatement pstm = conexion.prepareStatement(sql);
			pstm.setString(1, user);
			pstm.setString(2, pwd);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				// setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30 * 60);
				Cookie userName = new Cookie("ges_res.user", user);
				userName.setMaxAge(30 * 60);
				response.addCookie(userName);
				response.sendRedirect("index.jsp");
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
				PrintWriter out = response.getWriter();
				// out.println("<font color=red>Either user name or password is wrong."+"</font>");
				rd.include(request, response);
			}
		
			conexion.close();
		} catch (SQLException e) {
			// response.sendRedirect("login.jsp");
			response.getWriter().print(e.getLocalizedMessage());
		}
	}

}
```


### Identificando sesión

**Mapeado del Filtro: web.xml**: Indicamos las URLs que están protegidas (son interceptadas) por el filtro Java.

```xml
    <filter>
        <filter-name>UserFilter</filter-name>
        <filter-class>com.iesvdc.acceso.simplecrud.controller.AuthenticationFilter</filter-class>
    </filter>

    <filter-mapping>
        <filter-name>UserFilter</filter-name>
        <url-pattern>/user/*</url-pattern>
        <url-pattern>/installation/*</url-pattern>
        <url-pattern>/privado/*</url-pattern>
    </filter-mapping>
```

**Filter Java**: Implementación del filtro.

```java

public class AuthenticationFilter implements javax.servlet.Filter {
	public static final String AUTHENTICATION_HEADER = "Authorization";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filter) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;

			Cookie loginCookie = null;
			Cookie[] cookies = httpServletRequest.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("ges_res.user")) {
						loginCookie = cookie;
						break;
					}
				}
			}
			if (loginCookie != null) {
				filter.doFilter(request, response);
			} else {
				// ((HttpServletResponse)response.sendRedirect("login.jsp");
				if (response instanceof HttpServletResponse) {
					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					httpServletResponse.sendRedirect("/login.jsp");
				}
			}
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
```

### Cerrando sesión

**Servlet Logout**: Elimina la cookie.

```java
@WebServlet("/logout")
public class Logout extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("ges_res.user")) {
					loginCookie = cookie;
					break;
				}
			}
		}
		if (loginCookie != null) {
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);
		}
		response.sendRedirect("login.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

}
```