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
    <form:form>
        <form name="Invitation" >
            <div class="form-group">
                <form:input class="form-control" path="username" size="30" maxlength="80" placeholder="Friend's username"/>
            </div>
    <button type="submit" class="btn btn-default">Add player</button>
    </form>
    </form:form>

</petclinic:layout>
