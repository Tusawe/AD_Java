<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="reseniaList" dataSource="jdbc/biblioteca">
    select id, id_prestamo, comentario, fecha, estrellas from resenia;
</sql:query>


<%@ include file="../header.jsp" %>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de resenias</h2></caption>
            <tr>
                <th>id</th>
                <th>id_prestamo</th>
                <th>comentario</th>
                <th>fecha</th>
                <th>estrellas</th>
            </tr>
            <c:forEach var="resenia" items="${reseniaList.rows}">
                <tr>
                    <td><c:out value="${resenia.id}" /></td>
                    <td><c:out value="${resenia.id_prestamo}" /></td>
                    <td><c:out value="${resenia.comentario}" /></td>
                    <td><c:out value="${resenia.fecha}" /></td>
                    <td><c:out value="${resenia.estrellas}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
<%@ include file="../footer.jsp"%> 