# Tema 02: Conectividad con Bases de Datos Relacionales

## Preparando el entorno

Para completar esta práctica necesitamos tener instalada una JDK 1.8 o 
superior, Tomcat 8.5 o 9, MySQL Server, Maven, Git, y como IDE, 
Microsoft Visual Studio Code.

## Clonando el proyecto

```console

$ git clone https://gitlab.iesvirgendelcarmen.com/juangu/tema02GesAca

```

## Creamos la BBDD

```sql

- COMO ROOT PARA CREAR LA BBDD Y EL USUARIO
-- Mostrar los CHARSETs instalados:
SHOW CHARACTER SET;
-- Mostrar COLLATIONS instalados:
SHOW COLLATION;
-- CREAMOS LA BBDD PARA UTF-8 COLLATION EN ESPAÑOL
CREATE DATABASE gestion_academica  
	CHARACTER SET utf8 COLLATE utf8_spanish_ci;
-- CREAMOS EL USUARIO 
DROP USER damuser23;
CREATE USER damuser23 IDENTIFIED BY 'damuser';

-- DAMOS PERMISOS EN LA BBDD PARA EL NUEVO USUARIO
grant all privileges on gestion_academica.* to 'damuser23'@'%' identified by 'damuser';
grant all privileges on gestion_academica.* to 'damuser23'@'localhost' identified by 'damuser';
flush privileges;


-- COMO USUARIO PARA CREAR LAS TABLAS Y POBLAR DE DATOS
DROP TABLE IF EXISTS ALUM_ASIG;
DROP TABLE IF EXISTS PROF_ASIG;
DROP TABLE IF EXISTS ALUMNO;
DROP TABLE IF EXISTS PROFESOR;
DROP TABLE IF EXISTS ASIGNATURA;

CREATE TABLE ALUMNO (
	id INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(25) NOT NULL ,
	apellido VARCHAR(50) NOT NULL ) ;

CREATE TABLE PROFESOR (
	id INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(25) NOT NULL ,
	apellido VARCHAR(50) NOT NULL );

CREATE TABLE ASIGNATURA (
	id INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(25) NOT NULL ,
	curso SMALLINT,
	ciclo VARCHAR(50));

CREATE TABLE ALUM_ASIG (
	ASIGNATURA INT,
	ALUMNO INT,
    FALTAS INT,
	PRIMARY KEY(ASIGNATURA,ALUMNO),
	FOREIGN KEY (ALUMNO) REFERENCES ALUMNO(id) ,
	FOREIGN KEY (ASIGNATURA) REFERENCES ASIGNATURA(id) );

CREATE TABLE PROF_ASIG (
	ASIGNATURA INT,
	PROFESOR INT,
    HORAS_SEMANALES INT,
	PRIMARY KEY(ASIGNATURA, PROFESOR),
	FOREIGN KEY (PROFESOR) REFERENCES PROFESOR(id),
	FOREIGN KEY (ASIGNATURA) REFERENCES ASIGNATURA(id));

INSERT INTO ALUMNO VALUES (
	NULL,
	'PEPE',
	'PEREZ');
INSERT INTO PROFESOR VALUES (
	NULL,
	'JUAN',
	'GUTIERREZ');
INSERT INTO ASIGNATURA VALUES (
	NULL,
	'Acceso a Datos',
	2,
	'DAM'
);
```

## Conectar a la BBDD via JDBC

Necesitamos importar en la CLASS_PATH del proyecto el driver JDBC de MySQL.

```xml
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.13</version>
    </dependency>
```

Abrir la conexión:

```java
public class Conexion {
    Connection conn;
    public Conexion(){
        if (conn==null)
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_academica?" + 
                    "useUnicode=true&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&user=damuser23&password=damuser");
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);     
            }  
    }
    public Connection getConnection() {
        return conn;
    }
}
```

## CRUD (patrón DAO)

### Leer

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
