<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
    <div class="container">
        <form action="/user" method="POST">
            <input name="username" type="text" placeholder="Nombre de usuario" /><br>
            <input name="password" type="password" placeholder="Contraseña" /><br>
            <input name="dni" type="text" placeholder="DNI" /><br>
            <input name="nombre" type="text" placeholder="Nombre" /><br>
            <input name="apellido" type="text" placeholder="Apellido" /><br>
            <input name="email" type="email" placeholder="Correo electrónico" /><br>
            <input name="telefono" type="text" placeholder="Teléfono" /><br>
            <input name="tipo" type="text" placeholder="Tipo" /><br>
            <button type="submit">Enviar</button>
        </form>
    </div>
<%@ include file="../footer.jsp"%> 
