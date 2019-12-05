<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="instalacionList" dataSource="jdbc/gestionReservas">
    select nombre from instalacion;
</sql:query>


<%@ include file="../header.jsp" %>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de instalaciones</h2></caption>
            <tr>
                <th>Nombre</th>
            </tr>
            <c:forEach var="instalacion" items="${instalacionList.rows}">
                <tr>
                    <td><c:out value="${instalacion.nombre}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
<%@ include file="../footer.jsp"%> 
