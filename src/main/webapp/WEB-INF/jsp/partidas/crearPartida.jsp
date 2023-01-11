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

<style>
    body {
        background-image: url("/resources/images/background4.png");
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: 100% 100%;
    }

</style>

<petclinic:layout pageName="nuevaPartida">

    <h1><em><b><u>Nuevo juego</u></b></em></h1>
    
    <form:form>
        <h3 style="text-align: center;"><b><em>SELECCIONA MODO DE JUEGO</em></b></h3>
        <div class="col align-self-center">
            <a class="btn btn-default btn-lg" href="crearPartidaSolitaria" formmethod=" post"
            style="margin-top:4%; margin-bottom:4%; margin-left:22%; margin-right:22%; display:block;">MODO SOLITARIO</a>
        </div>

        <li class="dropdown" style="margin-top:4%; margin-bottom:4%; margin-left:22%; margin-right: 22%; display:block;">
            <a class="btn btn-default btn-lg" data-toggle="dropdown" 
            style="margin-top:4%; margin-bottom:4%; display:block;">
                <span>MODO MULTIJUGADOR</span>
                <span class="glyphicon glyphicon-chevron-down"></span>
            </a>
            <ul class="dropdown-menu" style="background-color:#34302D; min-width: 100%;">
                <li>
                    <a href="/lobby" class="btn btn-default" style="color:white; margin: 1%;">INVITAR</a>
                </li>
            </ul>
        </li>

    </form:form>

</petclinic:layout>
