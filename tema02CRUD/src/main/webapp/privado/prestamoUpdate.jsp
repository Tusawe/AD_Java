<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="prestamoList" dataSource="jdbc/biblioteca">
    select id, id_libro, id_usuario, prorroga from prestamo;
</sql:query>

<%@ include file="../header.jsp" %>
    <div id="paso1" align="center">
        <div class="form" id="prestamo-form">
            <caption><h2>Seleccione el prestamo que desea actualizar</h2></caption>
            <select id="prestamoId">
                <c:forEach var="prestamo" items="${prestamoList.rows}">
                    <option value="${prestamo.id}"> 
                        <c:out value="Libro: ${prestamo.id_libro}" />
                        / 
                        <c:out value="Usuario: ${prestamo.id_usuario}" />
                    </option>
                </c:forEach>
            </select>
        </div>
        <button onclick="recargar()">Enviar</button>
    </div>

    <div id="paso2" align="center" hidden>
        <div class="form">
            <input id="prestamoid" type="number" disabled /> <br/>
            <input id="prorroga" type="number" placeholder="Prórroga" /> <br/>
            <button onclick="enviar()">Enviar</button>
        </div>
    </div>

<script type="text/javascript">

$("#prestamo-form > option").first().attr("selected", true);
// $("#user-form > option").first().prop("selected", true);

function recargar(){
    var $form = $("#prestamo-form");
    var prestamoId = $("#prestamoId").val();
    var id_libro = $("#prestamoId option:selected").text();
    var url = 'http://localhost:9090/prestamo/'+prestamoId;
    
    console.log('Intentando borrar prestamo: id='+prestamoId+' id_libro='+id_libro);
    $.ajax({
        type : 'GET',
        url : url,
        contentType: 'application/json',
        // data : JSON.stringify({name: userName, email: userEmail}),
        success : function(data, status, xhr){
            console.log(data);
            let prestamo = data;
            $("#paso1").hide();
            $("#paso2").show();
            $("#prorroga").val(prestamo.prorroga);
            $("#prestamoid").val(prestamo.id);
            
        },
        error: function(xhr, status, error){
        $('#msg').html('<span style=\'color:red;\'>'+error+'</span>')
        }
    });
};


function enviar(){
    var url = 'http://localhost:9090/prestamo/'+$("#prestamoId").val();
    var datos = {};
    datos.id_libro = $("#id_libro").val();
    datos.id_usuario = $("#id_usuario").val();
    datos.prorroga = $("#prorroga").val();
    datos.id = $("#prestamoId").val();

    $.ajax({
        type : 'PUT',
        url : url,
        contentType: 'application/json',
        data : JSON.stringify(datos),
        success : function(data, status, xhr){
            window.location.replace("..");            
        },
        error: function(xhr, status, error){
            $('#msg').html('<span style=\'color:red;\'>'+error+'</span>');
        }
    });
}
</script>


<%@ include file="../footer.jsp"%>