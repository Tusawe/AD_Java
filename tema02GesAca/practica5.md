# Práctica 5

## Introducción

## Sistemas de autenticación

A la hora de comprobar los datos de acceso de los  usuarios de nuestra 
aplicación,  podemos usar los usuarios de Tomcat, un fichero de texto plano, un fichero XML, 
un directorio LDAP, un token o una cookie que nos envían tras un proceso de login, 
OAuth (Facebook, Google, Linkedin....), autenticación *Basic* en cada petición contra 
una tabla en una base de datos, etc.

Además, podemos decidir si delegamos en el servidor Web para gestionar la seguridad ("Tomcat Users" o Realm contra una base de datos tirando de DataSource, JNDI para consultar un LDAP...).   

## Uso de Filter para asegurar que estamos autenticados

Cuando queremos acceder a un recurso en una zona de nuestra aplicación Web que esté protegido, lo más sencillo es enviar en las cabeceras información de autenticación (métido "Basic").

Mira en [este enlace](https://gitlab.iesvirgendelcarmen.com/juangu/GestionAcademicaJDBC/tree/master/src/java/com/iesvdc/acceso/controller/filters) un ejemplo de cómo crear el filtro y el servicio de autenticación.

El cliente HTML5+JavaScript (usando el paradigma "Single Application Page"), tendrá que anviar información de autenticación en cada petición al servicio REST en las cabeceras: 

```javascript 
/**
 * Función para gestionar la autorización contra el servidor.
 * Puedes cambiarla para hacerlo con sesiones, sessionStorage, token, OAuth...
 * @param {type} xhr las cabeceras
 */
$.controller.authorize = function(xhr) {
    xhr.setRequestHeader ("Authorization", "Basic " +  btoa($.controller.username+":"+$.controller.password));
};

```

## Activar un filtro LOGIN y mantener la sesión

Cuando nos enfrentamos a una WebApp clásica **(sin servicio REST)** una manera más natural es usar cookies y sesiones.

#### login.jsp

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Crunchify Login Form - Session Management by Cookies</title>
 
<style type="text/css">

</style>
</head>
 
<body> 
    <div align="center">
        <br> <br>
        <form action="login" method="post">
            Usuario: <input type="text" name="usuario">
            <br> Password: <input type="password"
                name="password"> <br> <br> <br> <input
                type="submit" value="Login">
        </form>
    </div>
</body>
</html>
```


#### Login.java

En este ejemplo vamos a añadir simplemente una cookie llamada "gesacauser" a la que vamos a asignar el valor "admin":

```java
package com.iesvdc.acceso.controlador;
 
import java.io.IOException;
// import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String userID = "admin";
    private final String password = "1234";
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        // get request parameters for userID and password
        String usuario = request.getParameter("usuario");
        String pwd = request.getParameter("password");
 
        if (userID.equals(usuario) && password.equals(pwd)) {
            Cookie usuarioCookies = new Cookie("gesacauser", usuario);
            // setting cookie to expiry in 60 mins
            usuarioCookies.setMaxAge(60 * 60);
            response.addCookie(usuarioCookies);
            response.sendRedirect("loginSuccess.jsp");
        } else {
            //RequestDispatcher rd = getServletContext().getRequestDispatcher("/Alumno");
            //rd.include(request, response);
            response.sendRedirect("login.jsp");
        }
 
    }
}
```

#### Logout.java

```java
package com.iesvdc.acceso.controlador;
 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Cookie loginCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("gesacauser")) {
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
 
}
```


### Crear el filtro y el servicio de autenticación

#### Modificamos el Web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="3.1" 
    xmlns="http://xmlns.jcp.org/xml/ns/javaee" 
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd">
    
   <display-name>Gestión Académica</display-name>
    <welcome-file-list>
        <welcome-file>login.jsp</welcome-file>
    </welcome-file-list>
```



