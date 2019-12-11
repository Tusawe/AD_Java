<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
    <div class="container">
        <form action="/libro" method="POST">
            <input name="isbn10" type="text" placeholder="ISBN 10" /><br>
            <input name="isbn13" type="text" placeholder="ISBN 13" /><br>
            <input name="titulo" type="text" placeholder="Titulo" /><br>
            <input name="editorial" type="text" placeholder="Editorial" /><br>
            <input name="fechaPublicacion" type="text" placeholder="Año de publicación" /><br>
            <input name="nPaginas" type="number" placeholder="Nº de páginas" /><br>
            <button type="submit">Enviar</button>
        </form>
    </div>
<%@ include file="../footer.jsp"%> 