<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="installationList" dataSource="jdbc/gestionReservas">
    select id, nombre from instalacion;
</sql:query>


<%@ include file="../header.jsp" %>
    <div align="center">
        <form id="user-form">
            <caption><h2>Seleccione la instalación que desea eliminar</h2></caption>
            <select id="installationId">
                <c:forEach var="installation" items="${installationList.rows}">
                    <option value="${installation.id}"/> 
                        <c:out value="${installation.nombre}" />
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
    var installationId = $("#installationId").val();
    var installationName = $("#installationId option:selected").text();
    var url = 'http://localhost:9090/installation/'+installationId;

    console.log('Intentando borrar usuario: id='+installationId+' username='+installationName);
    $.ajax({
        type : 'DELETE',
        url : url,
        contentType: 'application/json',
        // data : JSON.stringify({name: userName, email: userEmail}),
        success : function(data, status, xhr){
            window.location.replace("../");
        },
        error: function(xhr, status, error){
        $('#msg').html('<span style=\'color:red;\'>'+error+'</span>')
        }
    });
};
</script>
<%@ include file="../footer.jsp"%> 
