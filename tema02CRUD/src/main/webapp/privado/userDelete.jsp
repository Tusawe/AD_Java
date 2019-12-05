<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="userList" dataSource="jdbc/biblioteca">
    select id, username, tipo, password from usuario;
</sql:query>


<%@ include file="../header.jsp" %>
    <div align="center">
        <form id="user-form">
            <caption><h2>Seleccione el usuario que desea eliminar</h2></caption>
            <select id="userId">
                <c:forEach var="usuario" items="${userList.rows}">
                    <option value="${usuario.id}"/> 
                        <c:out value="${usuario.username}" />
                        / 
                        <c:out value="${usuario.tipo}" />
                    </option>
                </c:forEach>
            </select>
        </form>
        <button onclick="enviar()">Enviar</button>
    </div>

    <div id="msg"></div>

<script type="text/javascript">

$("#user-form > option").first().attr("selected", true);
// $("#user-form > option").first().prop("selected", true);

function enviar(){
    var $form = $("#user-form");
    var userId = $("#userId").val();
    var username = $("#userId option:selected").text();
    var url = 'http://localhost:9090/user/'+userId;
    //var userName = $form.find('input[name="name"]').val();
    //var userEmail = $form.find('input[name="email"]').val();
    console.log('Intentando borrar usuario: id='+userId+' username='+username);
    $.ajax({
        type : 'DELETE',
        url : url,
        contentType: 'application/json',
        // data : JSON.stringify({name: userName, email: userEmail}),
        success : function(data, status, xhr){
            // window.location.replace("http://localhost:8080/users/"+userId);
            window.location.replace(".");
        },
        error: function(xhr, status, error){
        $('#msg').html('<span style=\'color:red;\'>'+error+'</span>')
        }
    });
};
</script>



<%@ include file="../footer.jsp"%> 
