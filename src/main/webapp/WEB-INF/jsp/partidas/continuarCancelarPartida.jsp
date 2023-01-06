<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<style>
    body {
        background-image: url("/resources/images/background4.png");
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: 100% 100%;
    }
</style>

<petclinic:layout pageName="continuarCancelarPartida">

    <h1><em><b><u>Juego en proceso</u></b></em></h1>
    
    <form:form>
        <div class="form-group">

            <div class="col align-self-center">
                <a class="btn btn-default btn-lg" href="/partida/continuarPartida"
                    style="margin-top:8%; margin-bottom:3%; margin-left:22%; margin-right:22%; display:block;"><b>Continuar</b></a>
            </div>
             
        </div>
        <div class="form-group">

            <div class="col align-self-center">
                <a class="btn btn-default btn-lg" href="/partida/cancelarPartida"
                    style="margin-top:3%; margin-bottom:3.3%; margin-left:22%; margin-right:22%; display:block;"><b>Cancelar</b></a>
            </div>
             
        </div>
    </form:form>

</petclinic:layout>
