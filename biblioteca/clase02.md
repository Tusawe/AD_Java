# Clase 2

## Creando los Plain Old Java Objects

Para poder hacer el marshalling/unmarshalling de objetos, necesitamos primero tener los objetos Java en memoria. 

A tal efecto creamos clases sencillas como Usuario, Instalacion, Reserva, etc. con sus correspondientes constructores, getters y setters.

Ahora ya es posible con Gson, por ejemplo, desde el servlet directamente hacer el marshalling/unmarshalling de JSON a Java.

## Conectando a la base de datos cargando la configuración vía JNDI 

Aprovechamos también para cargar la conexión por JNDI (Java Naming and Directory Interface), es decir pedimos a Java que localice, en el contexto actual, un objeto que será la información de la conexión a la base de datos.

En un entorno genérico, si por ejemplo queremos usar el patrón singleton y usar un único objeto conexión en nuestro código, podríamos hacer lo siguiente:

Abrir la conexión:

```java
public class Conexion {
    Connection conn;
    public Conexion(){
        if (conn==null)
            try {
                Class.forName("com.mysql.jdbc.Driver");
                this.conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_reservas?" + 
                    "useUnicode=true&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&user=root&password=example");
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);     
            }  
    }
    public Connection getConnection() {
        return conn;
    }
}
```

Sin embargo, si queremos abrir una conexión cargando la configuración desde una consulta al contexto de la aplicación, lo haríamos así:

```java

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author juangu
 */
public class Conexion {

    Connection conn;
    Context ctx;
    DataSource ds;

    public Conexion() {
        // Vía DataSource con Contexto inyectado
        try {
            if (ctx == null)
                ctx = new InitialContext();
            if (ds == null)
                ds = (DataSource) ((Context) ctx.lookup("java:comp/env")).lookup("jdbc/gestionReservas");
            conn = ds.getConnection();
        } catch (Exception ex) {
            System.out.println("## Conexion ERROR ## " + ex.getLocalizedMessage());
            ctx = null;
            ds = null;
            conn = null;
        }
    }

    public Connection getConnection() {
        return conn;
    }
}

```


## Conexión dedicada: Inicializar y cerrar la conexión desde  un servlet

Cuando hemos de tomar la decisión de cuando conectar a la base de datos desde un servlet, nos encontramos frente cuatro opciones:

1. **Conexión por transacción**: dentro de cada método doGet, doPost, doPut o doDelete abrimos y cerramos la conexión. En el método init() del servlet cargamos el driver JDBC correspondiente (Oracle, MySQL, PostgreSQL, etc.).
2. **Conexión dedicada**: al crear el servlet abrimos la conexión (método init() del mismo) y se cierra al descargar el servlet (método destroy()). Por tanto el driver y la conexión se cargan en el método init(). 
3. **Conexión por sesión**: cargamos el driver JDBC necesario en el método init(). No abrimos la conexión hasta el primer do(Get|Put|Delete|Post). En la sesión de usuario vamos pasando la conexión abierta de unos métodos a otros.
4. **Conexión cacheada**: Con un "pool" de conexiones. El servidor de aplicaciones (Tomcat, Jetty, Glashfish...) es el encargado de cargar el driver y abrir la conexión la primera vez que se necesita y ofrecerla a cada servlet que la necesita.

Aunque lo normal es delegar en el servidor de aplicaciones la gestión de conexiones al servidor de base de datos, una receta muy común es abrir y cerrar la conexión desde los métodos init() y destroy() de los mismos. Esto es lo que se llama una conexión dedicada. Veámoslo en los siguientes ejemplos:


### Abrir la conexión desde el método init()

```java
public class Alumno extends HttpServlet {

    Connection conn;

    @Override 
    public void init() throws ServletException {
        Conexion conexion = new Conexion(); 
        this.conn = conexion.getConnection();
    }
```

### Cerrar la conexión desde el método destroy()

```java
@Override
    public void destroy() {
        try {
            this.conn.close();
        } catch (SQLException ex) {

        }
    }
```
