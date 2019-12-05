<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<div class="container">
    <div class="jumbotron"><h2>Login</h2></div>
    <div class="form">
        <form action="login" method="POST">
            <input name="username" type="text" placeholder="Nombre de usuario" />
            <input name="pwd" type="password" placeholder="Contraseña" />
            <button type="submit">Enviar</button>
        </form>
    </div>
</div>
<%@ include file="footer.jsp"%>