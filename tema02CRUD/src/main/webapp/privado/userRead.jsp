<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="userList" dataSource="jdbc/biblioteca">
    select id, username, password, dni, nombre, apellido, email, telefono, tipo from usuario;
</sql:query>


<%@ include file="../header.jsp" %>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de usuarios</h2></caption>
            <tr>
                <th>id</th>
                <th>username</th>
                <th>password</th>
                <th>dni</th>
                <th>nombre</th>
                <th>apellido</th>
                <th>email</th>
                <th>telefono</th>
                <th>tipo</th>
            </tr>
            <c:forEach var="usuario" items="${userList.rows}">
                <tr>
                        <td><c:out value="${usuario.id}" /></td>
                    <td><c:out value="${usuario.username}" /></td>
                    <td><c:out value="${usuario.password}" /></td>
                    <td><c:out value="${usuario.dni}" /></td>
                    <td><c:out value="${usuario.nombre}" /></td>
                    <td><c:out value="${usuario.apellido}" /></td>
                    <td><c:out value="${usuario.email}" /></td>
                    <td><c:out value="${usuario.telefono}" /></td>
                    <td><c:out value="${usuario.tipo}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
<%@ include file="../footer.jsp"%> 
