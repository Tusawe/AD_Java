<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
    <div class="container">
        <form action="/prestamo" method="POST">
            <input name="id_libro" type="number" placeholder="Libro" /><br>
            <input name="id_usuario" type="number" placeholder="Usuario" /><br>
            <input name="fechaInicio" type="date" placeholder="Fecha de inicio" /><br>
            <input name="fechaEntrega" type="date" placeholder="Fecha de entrega" /><br>
            <input name="prorroga" type="number" placeholder="Prórroga" /><br>
            <input name="estado" type="text" placeholder="Estado" /><br>
            <button type="submit">Enviar</button>
        </form>
    </div>
<%@ include file="../footer.jsp"%> 