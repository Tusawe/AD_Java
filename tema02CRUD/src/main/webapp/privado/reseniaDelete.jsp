<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="reseniaList" dataSource="jdbc/biblioteca">
    select id, id_prestamo, fecha from resenia;
</sql:query>


<%@ include file="../header.jsp" %>
    <div align="center">
        <form id="resenia-form">
            <caption><h2>Seleccione el resenia que desea eliminar</h2></caption>
            <select id="reseniaId">
                <c:forEach var="resenia" items="${reseniaList.rows}">
                    <option value="${resenia.id}"/> 
                        <c:out value="Prestamo: ${resenia.id_prestamo}" />
                        / 
                        <c:out value="${resenia.fecha}" />
                    </option>
                </c:forEach>
            </select>
        </form>
        <button onclick="enviar()">Enviar</button>
    </div>

    <div id="msg"></div>

<script type="text/javascript">

$("#resenia-form > option").first().attr("selected", true);
// $("#user-form > option").first().prop("selected", true);

function enviar(){
    var $form = $("#resenia-form");
    var reseniaId = $("#reseniaId").val();
    var id_prestamo = $("#reseniaId option:selected").text();
    var url = 'http://localhost:9090/resenia/'+reseniaId;
    //var userName = $form.find('input[name="name"]').val();
    //var userEmail = $form.find('input[name="email"]').val();
    console.log('Intentando borrar resenia: id='+reseniaId+' id_prestamo='+id_prestamo);
    $.ajax({
        type : 'DELETE',
        url : url,
        contentType: 'application/json',
        // data : JSON.stringify({name: userName, email: userEmail}),
        success : function(data, status, xhr){
            // window.location.replace("http://localhost:8080/users/"+userId);
            window.location.replace("..");
        },
        error: function(xhr, status, error){
        $('#msg').html('<span style=\'color:red;\'>'+error+'</span>')
        }
    });
};
</script>



<%@ include file="../footer.jsp"%> 