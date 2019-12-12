<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="prestamoList" dataSource="jdbc/biblioteca">
    select pre.id as id , pre.id_libro as id_libro, pre.id_usuario as id_usuario, pre.fechaInicio as fechaInicio, pre.fechaEntrega as fechaEntrega, pre.prorroga as prorroga, pre.estado as estado, lib.titulo as titulo, usu.username as username from prestamo pre, usuario usu, libro lib where pre.id_libro = lib.id and pre.id_usuario = usu.id;
</sql:query>


<%@ include file="../header.jsp" %>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de prestamos</h2></caption>
            <tr>
                <th>id</th>
                <th>libro</th>
                <th>usuario</th>
                <th>fechaInicio</th>
                <th>fechaEntrega</th>
                <th>prorroga</th>
                <th>estado</th>
            </tr>
            <c:forEach var="prestamo" items="${prestamoList.rows}">
                <tr>
                        <td><c:out value="${prestamo.id}" /></td>
                    <td><c:out value="${prestamo.titulo}" /></td>
                    <td><c:out value="${prestamo.username}" /></td>
                    <td><c:out value="${prestamo.fechaInicio}" /></td>
                    <td><c:out value="${prestamo.fechaEntrega}" /></td>
                    <td><c:out value="${prestamo.prorroga}" /></td>
                    <td><c:out value="${prestamo.estado}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
<%@ include file="../footer.jsp"%> 