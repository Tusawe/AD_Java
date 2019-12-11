<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
    <div class="container">
        <form action="/resenia" method="POST">
            <input name="id_prestamo" type="number" placeholder="Prestamo" /><br>
            <input name="comentario" type="text" placeholder="Comentario" /><br>
            <input name="fecha" type="date" placeholder="Fecha" /><br>
            <input name="estrellas" type="number" placeholder="Estrellas" /><br>
            <button type="submit">Enviar</button>
        </form>
    </div>
<%@ include file="../footer.jsp"%> 