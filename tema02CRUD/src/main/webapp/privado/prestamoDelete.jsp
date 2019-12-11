<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="prestamoList" dataSource="jdbc/biblioteca">
    select id, id_libro, id_usuario from prestamo;
</sql:query>


<%@ include file="../header.jsp" %>
    <div align="center">
        <form id="prestamo-form">
            <caption><h2>Seleccione el prestamo que desea eliminar</h2></caption>
            <select id="prestamoId">
                <c:forEach var="prestamo" items="${prestamoList.rows}">
                    <option value="${prestamo.id}"/> 
                        <c:out value="Libro: ${prestamo.id_libro}" />
                        / 
                        <c:out value="Usuario: ${prestamo.id_usuario}" />
                    </option>
                </c:forEach>
            </select>
        </form>
        <button onclick="enviar()">Enviar</button>
    </div>

    <div id="msg"></div>

<script type="text/javascript">

$("#prestamo-form > option").first().attr("selected", true);
// $("#user-form > option").first().prop("selected", true);

function enviar(){
    var $form = $("#prestamo-form");
    var prestamoId = $("#prestamoId").val();
    var id_libro = $("#prestamoId option:selected").text();
    var url = 'http://localhost:9090/prestamo/'+prestamoId;
    //var userName = $form.find('input[name="name"]').val();
    //var userEmail = $form.find('input[name="email"]').val();
    console.log('Intentando borrar prestamo: id='+prestamoId+' id_libro='+id_libro);
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