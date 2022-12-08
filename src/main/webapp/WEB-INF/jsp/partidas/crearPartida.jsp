<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<script>
    function mostrarForm(){ 
    var formulario = document.getElementById('Invitation');
    formulario.style.display = "block";  
}
</script>


<petclinic:layout pageName="CREAR PARTIDA">
    <h2>CREACION DE PARTIDAS</h2>
    <form:form>
        <h3>SELECCIONA MODO DE JUEGO</h3>
        <div class="col-sm-offset-2 col-sm-10">
            <a class="btn btn-default" href="/prueba"  formmethod=" post">MODO SOLITARIO</a>
        </div>
        <div class="col-sm-offset-2 col-sm-10">
            <input type=button class="btn btn-default" onclick="mostrarForm()" value="MODO MULTIJUGADOR"/>
	                <div id="Invitation" style="display: none;" class="modal-content">
                <form name="Invitation" >
                        <div class="form-group">
                            <form:input class="form-control" path="username" size="30" maxlength="80" placeholder="Friend's username"/>
                        </div>
	            <button type="submit" class="btn btn-default">Add player</button>
		        </form>
        </div>
    </form:form>

</petclinic:layout>
