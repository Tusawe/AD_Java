# Clase 1

## Creamos la BBDD

```sql

-- COMO ROOT PARA CREAR LA BBDD Y EL USUARIO

-- CREAMOS LA BBDD PARA UTF-8 COLLATION EN ESPAÑOL
CREATE DATABASE gestion_reservas  \
	CHARACTER SET utf16 COLLATE utf16_spanish_ci;

-- CAMBIAMOS LA BBDD ACTIVA
USE gestion_reservas;


-- CREAMOS LAS TABLAS
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `username` varchar(12) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE='InnoDB';

CREATE TABLE `instalacion` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nombre` varchar(50) NOT NULL
) ENGINE='InnoDB';

CREATE TABLE `horario` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `instalacion` int(11) NOT NULL,
  `inicio` time NOT NULL,
  `fin` time NOT NULL,
  FOREIGN KEY (`instalacion`) REFERENCES `instalacion` (`id`) ON DELETE CASCADE
) ENGINE='InnoDB';

CREATE TABLE `reserva` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `usuario` int(11) NOT NULL,
  `horario` int(11) NOT NULL,
  `fecha` date NOT NULL,
  FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`),
  FOREIGN KEY (`horario`) REFERENCES `horario` (`id`)
) ENGINE='InnoDB';


```

## Creación del proyecto en modo interactivo (MAVEN)

Si has clonado este repositorio, no es necesario hacer este paso, sólamente cuando quieras crear un proyecto como éste.

Para crear en modo interactivo el proyecto, estructura de directorios, fichero pom.xml, etc.  **desde cero**, tendríamos que usar el proyecto Maven Java Web desde el IDE o bien desde línea de comandos ejecutaríamos esta instrucción:

```bash
$ mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-webapp -DarchetypeVersion=1.4
```

Tras indicar el grupo (com.iesvdc.acceso.simplecrud) y el artefacto (simplecrud) Maven crea los ficheros necesarios.

## Dependencias Maven

Antes de comenzar, veamos las dependencias (librerías) adicionales que va a necesitar nuestro proyecto.

### MySQL

Necesitamos importar en la CLASS_PATH del proyecto el driver JDBC de MySQL.

```xml
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.1</version>
    </dependency>
```

### Soporte J2EE (Servlets)

Para poder usar JSP (Java Server Pages) y Servlets (las clases necesarias), tenemos que cargar la API Web de Java:

```xml
    <dependency>
        <groupId>javax</groupId>
        <artifactId>javaee-web-api</artifactId>
        <version>8.0.1</version>
        <scope>provided</scope>
    </dependency>
```

### Soporte para JSLT (para JSP)

Para poder usar las extensiones JSLT dentro de una página JSP, necesitamos importar la API JSLT:

```xml
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
```

### Soporte JSON

Para hacer el marshalling/unmarshalling de objetos usaremos la API Gson de Google:

```xml
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.6</version>
    </dependency>
```

### Insertando el servidor Tomcat en Maven

Para poder ejecutar Tomcat desde maven para no necesitar descargar e instalar el servidor de aplicaciones Java de la Apache foundation, añadimos estas líneas dentro de "build->plugins":

```xml
    <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.2</version>
        <configuration>
            <port>9090</port>
            <path>/</path>
            </configuration>
    </plugin>
```

## Conectando a la Base de Datos

Abrir la conexión:

```java
public class Conexion {
    Connection conn;
    public Conexion(){
        if (conn==null)
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_reservas?" +
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

## CRUD básico

### LEER uno (findOne)

```java
    String jsonObject="{}";
    Connection conexion;
    PreparedStatement pstm;
    String jdbcURL;

    jdbcURL = JDBC_MYSQL_GESTION_RESERVAS;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion =  DriverManager.getConnection(jdbcURL, "root", "example");
        String sql = "SELECT * FROM usuario WHERE id=?";
        pstm = conexion.prepareStatement(sql);
        pstm.setInt(1, Integer.parseInt(id));
        ResultSet rs = pstm.executeQuery();
        if ( rs.next() ) {  
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            // String id = rs.getString("id");
            jsonObject="{"+ "\n"+
                "'id':'"+id+"',"+ "\n"+
                "'username':'"+username+"',"+ "\n"+
                "'password':'"+password+"',"+ "\n"+
                "'email':'"+email+"'"+ "\n"+
                "}";
        }
    }catch(Exception ex){
        // Gestión de la excepción
    }
    // devolvemos jsonObject
```

### LEER todos (findAll)

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="userList" dataSource="jdbc/gestionReservas">
    select username, email, password from usuario;
</sql:query>


<%@ include file="header.jsp" %>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de alumnos</h2></caption>
            <tr>
                <th>username</th>
                <th>email</th>
                <th>password</th>
            </tr>
            <c:forEach var="usuario" items="${userList.rows}">
                <tr>
                    <td><c:out value="${usuario.username}" /></td>
                    <td><c:out value="${usuario.email}" /></td>
                    <td><c:out value="${usuario.password}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
<%@ include file="footer.jsp"%>

```

### Crear

```java
    Connection conexion;
    PreparedStatement pstm;
    String jdbcURL;
    jdbcURL = JDBC_MYSQL_GESTION_RESERVAS;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion =  DriverManager.getConnection(jdbcURL, "root", "example");
        String sql = "INSERT INTO usuario (username,password,email) VALUES(?,?,?)";
        pstm = conexion.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        pstm.setString(3, email);
        if (pstm.executeUpdate() >0) {
            // "Usuario insertado"
        } else {
            // "No se ha podido insertar"
        }
        conexion.close();
    } catch (Exception ex) {
        // "Imposible conectar a la BBDD"
    }
```

### Actualizar

```java
    Usuario user = new Gson().fromJson(req.getReader(), Usuario.class);
    String jdbcURL = JDBC_MYSQL_GESTION_RESERVAS;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion =  DriverManager.getConnection(jdbcURL, "root", "example");
        String sql = "UPDATE usuario SET username=?, password=?, email=? WHERE id=?"; 
        PreparedStatement pstm = conexion.prepareStatement(sql);
        pstm.setString(1, user.getUsername());
        pstm.setString(2, user.getPassword());
        pstm.setString(3, user.getEmail());
        pstm.setInt(4, user.getId());

        if (pstm.executeUpdate() >0) {
            resp.getWriter().println("Usuario insertado");
        } else {
            resp.getWriter().println("No se ha podido insertar");
        }

        conexion.close();
    } catch (Exception ex) {
        resp.getWriter().println(ex.getMessage());
        resp.getWriter().println(ex.getLocalizedMessage());
        // resp.getWriter().println("Imposible conectar a la BBDD");
    }
```

### Borrar

```java
    Connection conexion;
    PreparedStatement pstm;
    String jdbcURL;

    jdbcURL = JDBC_MYSQL_GESTION_RESERVAS;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion =  DriverManager.getConnection(jdbcURL, "root", "example");
        String sql = "DELETE FROM usuario WHERE id=?";
        pstm = conexion.prepareStatement(sql);
        pstm.setInt(1, Integer.parseInt(id));
        if ( pstm.executeUpdate()==0 ) {  
            jsonObject="{ "+
                "'id':'"+id+"'}";
        }
    }catch(Exception ex){
        // "No se pudo eliminar"
    }
```

## CRUD (patrón DAO)

Partimos de una clase base Alumno (POJO) que contiene los objetos que 
vamos a almacenar/recuperar de la base de datos:

```java
public class AlumnoPOJO {
    private Integer id;
    private String nombre;
    private String apellido;

    public AlumnoPOJO() {
        this.id = null;
        this.nombre = null;
        this.apellido = null;
    }

    public AlumnoPOJO(String nombre, String apellido) {
        this.id = null;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public AlumnoPOJO(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    [.....]

```


### Leer todos 

```java
public List<AlumnoPOJO> findAll() {
        AlumnoPOJO al;
        List<AlumnoPOJO> li_al = new ArrayList();
        try {            
            // conectamos a la BBDD
            Conexion conexion = new Conexion();    
            Connection conn = conexion.getConnection();
            // esta es la cadena SQL de conslulta
            String sql = "SELECT * FROM ALUMNO";
            // usamos este objeto porque es más seguro
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // ejecutar la consulta contra la base de datos y 
            // devuelve el resultado en el ResultSet (parecido a 
            // un Array con iterador
            ResultSet rs  = pstmt.executeQuery();
            // recorro el resultset mientras tengo datos
            while (rs.next()){
                al = new AlumnoPOJO(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"));
                li_al.add(al);
            }
            // cerramos la conexión
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR"+ ex.getMessage());
            li_al = null;
        } 
        return li_al;
    }
```

### Crear

```java
public boolean create(AlumnoPOJO al){
        boolean exito=true;
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConnection();
            String sql =
                "INSERT INTO ALUMNO VALUES (NULL,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, al.getNombre());
            pstmt.setString(2, al.getApellido());
            pstmt.executeUpdate();  
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR:  "+ex.getMessage());
            exito = false;
        } 
        return exito;
    }
```

### Actualizar

```java
/**
     * Este método actualiza un alumno en la BBDD
     * @param old_id
     * El id antiguo del alumno 
     * @param new_al
     * El objeto que contiene al alumno actualizado
     * @return 
     * true si se lleva a cabo correctamente <br>
     * false si no se actualiza nada (error de conexión, no 
     * estaba el alumno en la BBDD...) <br>
     */
    public boolean update(Integer old_id, AlumnoPOJO new_al) {
        boolean exito=true;
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConnection();
            String sql = 
                "UPDATE ALUMNO SET id=?, nombre=?, apellido=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(4, old_id);
            pstmt.setInt(1, new_al.getId());
            pstmt.setString(2, new_al.getNombre());
            pstmt.setString(3, new_al.getApellido());
            if (pstmt.executeUpdate()==0) {
                exito = false;
            }
            conn.close();
        } catch (SQLException ex) {
            exito = false;
        }
        return exito;
    }
```

### Borrar

```java
 public boolean delete(Integer id_al){
        boolean exito=true;
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConnection();
            String sql = "DELETE FROM ALUMNO WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_al);
            if (pstmt.executeUpdate()==0) {
                exito = false;
            }
            conn.close();
        } catch (SQLException ex) {
            exito = false;
        } 
        return exito;
    }
```
