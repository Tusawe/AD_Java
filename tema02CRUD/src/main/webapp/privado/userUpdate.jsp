<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="userList" dataSource="jdbc/biblioteca">
    select id, username, tipo, password from usuario;
</sql:query>


<%@ include file="../header.jsp" %>
    <div id="paso1" align="center">
        <div class="form" id="user-form">
            <caption><h2>Seleccione el usuario que desea actualizar</h2></caption>
            <select id="userId">
                <c:forEach var="usuario" items="${userList.rows}">
                    <option value="${usuario.id}"/> 
                        <c:out value="${usuario.username}" />
                        / 
                        <c:out value="${usuario.tipo}" />
                    </option>
                </c:forEach>
            </select>
        </div>
        <button onclick="recargar()">Enviar</button>
    </div>

    <div id="paso2" align="center" hidden>
        <div class="form">
            <input id="userid" type="text" disabled /> <br/>
            <input id="username" type="text" placeholder="Nombre de usuario" /> <br/>
            <input id="password" type="text" placeholder="Contraseña" /><br/>
            <input id="dni" type="text" placeholder="DNI" /><br>
            <input id="nombre" type="text" placeholder="Nombre" /><br>
            <input id="apellido" type="text" placeholder="Apellido" /><br>
            <input id="email" type="email" placeholder="Correo electrónico" /><br>
            <input id="telefono" type="text" placeholder="Teléfono" /><br>
            <input id="tipo" type="text" placeholder="Tipo" /><br>
            <button onclick="enviar()">Enviar</button>
        </div>
    </div>

<script type="text/javascript">

$("#user-form > option").first().attr("selected", true);
// $("#user-form > option").first().prop("selected", true);

function recargar(){
    var $form = $("#user-form");
    var userId = $("#userId").val();
    var username = $("#userId option:selected").text();
    var url = 'http://localhost:9090/user/'+userId;
    
    console.log('Intentando borrar usuario: id='+userId+' username='+username);
    $.ajax({
        type : 'GET',
        url : url,
        contentType: 'application/json',
        // data : JSON.stringify({name: userName, email: userEmail}),
        success : function(data, status, xhr){
            console.log(data);
            let usuario = data;
            $("#paso1").hide();
            $("#paso2").show();
            $("#username").val(usuario.username);
            $("#password").val(usuario.password);
            $("#email").val(usuario.email);
            $("#userid").val(usuario.id);
            
        },
        error: function(xhr, status, error){
        $('#msg').html('<span style=\'color:red;\'>'+error+'</span>')
        }
    });
};


function enviar(){
    var url = 'http://localhost:9090/user/'+$("#userid").val();
    var datos = {};
    datos.username = $("#username").val();
    datos.password = $("#password").val();
    datos.email = $("#email").val();
    datos.id = $("#userid").val();

    $.ajax({
        type : 'PUT',
        url : url,
        contentType: 'application/json',
        data : JSON.stringify(datos),
        success : function(data, status, xhr){
            window.location.replace("..")            
        },
        error: function(xhr, status, error){
            $('#msg').html('<span style=\'color:red;\'>'+error+'</span>');
        }
    });
}
</script>


<%@ include file="../footer.jsp"%> 
