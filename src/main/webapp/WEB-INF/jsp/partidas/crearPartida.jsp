<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<script>
    function mostrarForm(){ 
    var formulario = document.getElementById('Invitation');
    formulario.style.display = "block"; 
    formulario.style.marginBottom = "0px"; 
    formulario.style.marginTop = "0px"; 
    formulario.style.marginLeft = "400px"; 
    formulario.style.marginRight = "300px"; 
    formulario.style.width = "80px";
    formulario.style.height = "37px";
    formulario.style.backgroundColor = "transparent";

}
</script>

<petclinic:layout pageName="nuevaPartida">

    <h1><em><b><u>Nuevo juego</u></b></em></h1>
    
    <form:form>
        <h3>SELECCIONA MODO DE JUEGO</h3>
        <div class="col-sm-offset-2 col-sm-10">
            <a class="btn btn-default btn-lg" href="crearPartidaSolitaria" formmethod=" post"
            style="margin-top:5%; margin-bottom:1%; margin-left:5%; margin-right:5%; display:block;">MODO SOLITARIO</a>
        </div>
        <div class="col-sm-offset-2 col-sm-10">
            <a type=button class="btn btn-default btn-lg" onclick="mostrarForm()"
            style="margin-top:5%; margin-bottom:1%; margin-left:5%; margin-right:5%; display:block;">MODO MULTIJUGADOR</a>
	            <div id="Invitation" style="display: none;" class="modal-content">
                    <form name="Invitation" >
                        <spring:url value="/lobby" htmlEscape="true" var="invite">
                        </spring:url>
                        <div class="col-sm-offset-2 col-sm-10">
                            <a class="btn btn-default" href="${invite}">INVITAR</a>
                        </div>
                    </form>
                </div>
        </div>
    </form:form>

</petclinic:layout>
