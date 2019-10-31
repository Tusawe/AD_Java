<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
    <div class="container">
        <form action="login" method="POST">
            <input name="username" type="text" placeholder="Nombre de usuario" />
            <input name="password" type="password" placeholder="Contraseña" />
            <button type="submit">Enviar</button>
        </form>
    </div>
<%@ include file="footer.jsp"%> 