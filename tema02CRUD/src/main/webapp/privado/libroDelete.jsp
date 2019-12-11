<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sql:query var="libroList" dataSource="jdbc/biblioteca">
    select id, titulo, editorial from libro;
</sql:query>


<%@ include file="../header.jsp" %>
    <div align="center">
        <form id="libro-form">
            <caption><h2>Seleccione el libro que desea eliminar</h2></caption>
            <select id="libroId">
                <c:forEach var="libro" items="${libroList.rows}">
                    <option value="${libro.id}"/> 
                        <c:out value="${libro.titulo}" />
                        / 
                        <c:out value="${libro.editorial}" />
                    </option>
                </c:forEach>
            </select>
        </form>
        <button onclick="enviar()">Enviar</button>
    </div>

    <div id="msg"></div>

<script type="text/javascript">

$("#libro-form > option").first().attr("selected", true);
// $("#user-form > option").first().prop("selected", true);

function enviar(){
    var $form = $("#libro-form");
    var libroId = $("#libroId").val();
    var titulo = $("#libroId option:selected").text();
    var url = 'http://localhost:9090/libro/'+libroId;
    //var userName = $form.find('input[name="name"]').val();
    //var userEmail = $form.find('input[name="email"]').val();
    console.log('Intentando borrar libro: id='+libroId+' titulo='+titulo);
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