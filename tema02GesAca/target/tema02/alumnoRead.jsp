<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     

<sql:query var="listaAlumnos" dataSource="jdbc/gestionAcademica">
    select nombre, apellido from ALUMNO;
</sql:query>

<%@ include file="extras/header.jsp" %>
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

    <%@ include file="extras/footer.jsp" %>