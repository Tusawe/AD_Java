<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<sql:query var="libroList" dataSource="jdbc/biblioteca">
    select id, titulo from libro;
</sql:query>

<sql:query var="userList" dataSource="jdbc/biblioteca">
    select id, username from usuario;
</sql:query>

<div class="container">
    <form action="/prestamo" method="POST">
        <select name="id_libro">
            <c:forEach var="libro" items="${libroList.rows}">
                <option value="${libro.id}"> 
                    <c:out value="${libro.titulo}" />
                </option>
            </c:forEach>
        </select><br>
        <select name="id_usuario">
            <c:forEach var="usuario" items="${userList.rows}">
                <option value="${usuario.id}"> 
                    <c:out value="${usuario.username}" />
                </option>
            </c:forEach>
        </select><br>
        <p>Fecha prestamo</p>
        <input name="fechaInicio" type="date" placeholder="Fecha inicio" /><br>
        <p>Fecha devolucion</p>
        <input name="fechaEntrega" type="date" placeholder="Fecha entrega" /><br>
        <select name="estado" >
                <option value="Perfecto">Perfecto</option>
            <option value="Bueno">Bueno</option>
            <option value="Regular">Regular</option>
            <option value="Malo">Malo</option>
            <option value="Dañado">Dañado</option>
            </select><br>
        <input name="prorroga" type="number" placeholder="prorroga" /><br>
        <button type="submit">Enviar</button>
    </form>
</div>  
<%@ include file="../footer.jsp"%> 