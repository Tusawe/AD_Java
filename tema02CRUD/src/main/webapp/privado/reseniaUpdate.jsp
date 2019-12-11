<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="reseniaList" dataSource="jdbc/biblioteca">
    select id, id_prestamo, fecha, comentario, estrellas from resenia;
</sql:query>

<%@ include file="../header.jsp" %>
    <div id="paso1" align="center">
        <div class="form" id="resenia-form">
            <caption><h2>Seleccione el resenia que desea actualizar</h2></caption>
            <select id="reseniaId">
                <c:forEach var="resenia" items="${reseniaList.rows}">
                    <option value="${resenia.id}"> 
                        <c:out value="Prestamo: ${resenia.id_prestamo}" />
                        / 
                        <c:out value="${resenia.fecha}" />
                    </option>
                </c:forEach>
            </select>
        </div>
        <button onclick="recargar()">Enviar</button>
    </div>

    <div id="paso2" align="center" hidden>
        <div class="form">
            <input id="reseniaid" type="number" disabled /> <br/>
            <input id="comentario" type="text" placeholder="Comentario" /> <br/>
            <input id="estrellas" type="number" placeholder="Estrellas" /> <br/>
            <button onclick="enviar()">Enviar</button>
        </div>
    </div>

<script type="text/javascript">

$("#resenia-form > option").first().attr("selected", true);
// $("#user-form > option").first().prop("selected", true);

function recargar(){
    var $form = $("#resenia-form");
    var reseniaId = $("#reseniaId").val();
    var id_prestamo = $("#reseniaId option:selected").text();
    var url = 'http://localhost:9090/resenia/'+reseniaId;
    
    console.log('Intentando borrar resenia: id='+reseniaId+' id_prestamo='+id_prestamo);
    $.ajax({
        type : 'GET',
        url : url,
        contentType: 'application/json',
        // data : JSON.stringify({name: userName, email: userEmail}),
        success : function(data, status, xhr){
            console.log(data);
            let resenia = data;
            $("#paso1").hide();
            $("#paso2").show();
            $("#id_prestamo").val(resenia.id_prestamo);
            $("#comentario").val(resenia.comentario);
            $("#estrellas").val(resenia.estrellas);
            $("#reseniaid").val(resenia.id);
            
        },
        error: function(xhr, status, error){
        $('#msg').html('<span style=\'color:red;\'>'+error+'</span>')
        }
    });
};


function enviar(){
    var url = 'http://localhost:9090/resenia/'+$("#reseniaId").val();
    var datos = {};
    datos.id_prestamo = $("#id_prestamo").val();
    datos.comentario = $("#comentario").val();
    datos.estrellas = $("#estrellas").val();
    datos.id = $("#reseniaId").val();

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