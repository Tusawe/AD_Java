<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="libroList" dataSource="jdbc/biblioteca">
    select id, titulo, editorial from libro;
</sql:query>

<%@ include file="../header.jsp" %>
    <div id="paso1" align="center">
        <div class="form" id="libro-form">
            <caption><h2>Seleccione el libro que desea actualizar</h2></caption>
            <select id="libroId">
                <c:forEach var="libro" items="${libroList.rows}">
                    <option value="${libro.id}"> 
                        <c:out value="${libro.titulo}" />
                        / 
                        <c:out value="${libro.editorial}" />
                    </option>
                </c:forEach>
            </select>
        </div>
        <button onclick="recargar()">Enviar</button>
    </div>

    <div id="paso2" align="center" hidden>
        <div class="form">
            <input id="libroid" type="number" disabled /> <br/>
            <input id="titulo" type="text" placeholder="Título del libro" /> <br/>
            <input id="editorial" type="text" placeholder="Editorial" /> <br/>
            <button onclick="enviar()">Enviar</button>
        </div>
    </div>

<script type="text/javascript">

$("#libro-form > option").first().attr("selected", true);
// $("#user-form > option").first().prop("selected", true);

function recargar(){
    var $form = $("#libro-form");
    var libroId = $("#libroId").val();
    var titulo = $("#libroId option:selected").text();
    var url = 'http://localhost:9090/libro/'+libroId;
    
    console.log('Intentando editar el libro: id='+libroId+' titulo='+titulo);
    $.ajax({
        type : 'GET',
        url : url,
        contentType: 'application/json',
        success : function(data, status, xhr){
            console.log(data);
            let libro = data;
            $("#paso1").hide();
            $("#paso2").show();
            $("#titulo").val(libro.titulo);
            $("#editorial").val(libro.editorial);
            $("#libroid").val(libro.id);
            
        },
        error: function(xhr, status, error){
        $('#msg').html('<span style=\'color:red;\'>'+error+'</span>')
        }
    });
};


function enviar(){
    var url = 'http://localhost:9090/libro/'+$("#libroId").val();
    var datos = {};
    datos.titulo = $("#titulo").val();
    datos.editorial = $("#editorial").val();
    datos.id = $("#libroId").val();

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