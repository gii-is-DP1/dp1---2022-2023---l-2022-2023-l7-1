<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<script>
    function mostrarForm(){ 
    var formulario = document.getElementById('Invitation');
    formulario.style.display = "block";  
    formulario.style.height = "50px";
    formulario.style.width = "300px";
}
</script>


<petclinic:layout pageName="CREAR PARTIDA">
    <h2>CREACION DE PARTIDAS</h2>
    <form:form>
        <h3>SELECCIONA MODO DE JUEGO</h3>
        <div class="col-sm-offset-2 col-sm-10">
            <a class="btn btn-default" href="crearPartidaSolitaria"  formmethod=" post">MODO SOLITARIO</a>
        </div>
        <div class="col-sm-offset-2 col-sm-10">
            <input type=button class="btn btn-default" onclick="mostrarForm()" value="MODO MULTIJUGADOR"/>
	                <div id="Invitation" style="display: none;" class="modal-content">
                <form name="Invitation" >
                    <spring:url value="/${username}/lobby" htmlEscape="true" var="invite">
                     </spring:url>
                     <div class="col-sm-offset-2 col-sm-10">
                        <a class="btn btn-default" href="${invite}">INVITAR</a>
                     </div>
		        </form>
        </div>
    </form:form>

</petclinic:layout>
