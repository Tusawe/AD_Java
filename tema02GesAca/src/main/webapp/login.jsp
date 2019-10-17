<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="extras/header.jsp" %>
    <div align="center">
        <br> <br>
        <form action="login" method="post">
            Usuario: <input type="text" name="usuario">
            <br> Password: <input type="password"
                name="password"> <br> <br> <br> <input
                type="submit" value="Login">
        </form>
    </div>
<%@ include file="extras/footer.jsp" %> 