<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="continuarCancelarPartida">

    <h1><em><b><u>Game in progress</u></b></em></h1>
    
    <form:form>
        <div class="col-sm-offset-2 col-sm-10">
            <a class="btn btn-default" href="/partida/continuarPartida"  formmethod=" post">Continue</a>
        </div>
    </form:form>
    <form:form>
        <div class="col-sm-offset-2 col-sm-10">
            <a class="btn btn-default" href="/partida/cancelarPartida"  formmethod=" post">Cancel</a>
        </div>
    </form:form>

</petclinic:layout>
