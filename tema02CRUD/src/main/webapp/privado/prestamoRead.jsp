<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="prestamoList" dataSource="jdbc/biblioteca">
    select id, id_libro, id_usuario, fechaInicio, fechaEntrega, prorroga, estado from prestamo;
</sql:query>


<%@ include file="../header.jsp" %>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de prestamos</h2></caption>
            <tr>
                <th>id</th>
                <th>id_libro</th>
                <th>id_usuario</th>
                <th>fechaInicio</th>
                <th>fechaEntrega</th>
                <th>prorroga</th>
                <th>estado</th>
            </tr>
            <c:forEach var="prestamo" items="${prestamoList.rows}">
                <tr>
                        <td><c:out value="${prestamo.id}" /></td>
                    <td><c:out value="${prestamo.id_libro}" /></td>
                    <td><c:out value="${prestamo.id_usuario}" /></td>
                    <td><c:out value="${prestamo.fechaInicio}" /></td>
                    <td><c:out value="${prestamo.fechaEntrega}" /></td>
                    <td><c:out value="${prestamo.prorroga}" /></td>
                    <td><c:out value="${prestamo.estado}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
<%@ include file="../footer.jsp"%> 