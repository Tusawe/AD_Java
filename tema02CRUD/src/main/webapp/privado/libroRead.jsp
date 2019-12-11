<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="libroList" dataSource="jdbc/biblioteca">
    select id, isbn10, isbn13, titulo, editorial, fechaPublicacion, nPaginas from libro;
</sql:query>


<%@ include file="../header.jsp" %>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de libros</h2></caption>
            <tr>
                <th>id</th>
                <th>isbn10</th>
                <th>isbn13</th>
                <th>titulo</th>
                <th>editorial</th>
                <th>fechaPublicacion</th>
                <th>nPaginas</th>
            </tr>
            <c:forEach var="libro" items="${libroList.rows}">
                <tr>
                        <td><c:out value="${libro.id}" /></td>
                    <td><c:out value="${libro.isbn10}" /></td>
                    <td><c:out value="${libro.isbn13}" /></td>
                    <td><c:out value="${libro.titulo}" /></td>
                    <td><c:out value="${libro.editorial}" /></td>
                    <td><c:out value="${libro.fechaPublicacion}" /></td>
                    <td><c:out value="${libro.nPaginas}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
<%@ include file="../footer.jsp"%> 