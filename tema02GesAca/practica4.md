# Práctica 4

## Crear una página JSP para dar un listado de alumnos

Una vez configurada la conexión a la base de datos desde un DataSource, 
como hemos hecho en el ejercicio anterior, ya podemos usarla desde 
cualquier servlet o página JSP.

### Añadir soporte JSTL 

Para poder **"inyectar"** código SQL o Java en una página JSP vamos
a habilitar el motor JSTL. En el pom.xml del proyecto añadimos la dependencia:

```xml
<!-- https://mvnrepository.com/artifact/jstl/jstl -->
<!-- Para que funcione JSTL (con JSP) -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>
```

### Listado de alumnos desde JSP

```html

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<sql:query var="listaAlumnos" dataSource="jdbc/gestionAcademica">
    select nombre, apellido from ALUMNO;
</sql:query>
     
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Alumnos</title>
</head>
<body>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de alumnos</h2></caption>
            <tr>
                <th>Nombre</th>
                <th>Apellidos</th>
            </tr>
            <c:forEach var="alumno" items="${listaAlumnos.rows}">
                <tr>
                    <td><c:out value="${alumno.nombre}" /></td>
                    <td><c:out value="${alumno.apellido}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>

```