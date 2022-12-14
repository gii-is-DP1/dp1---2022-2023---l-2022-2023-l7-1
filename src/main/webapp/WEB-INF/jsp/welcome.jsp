<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<style>
    body {
        background-image: url("/resources/images/background4.png");
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: 100% 100%;
    }
</style>

<petclinic:layout pageName="home">         

        <sec:authorize access="isAuthenticated()">
            <div class="form-group">

                <div class="col align-self-center">
                    <a class="btn btn-default btn-lg" href="/partida/crearPartida"
                        style="margin-top:10%; margin-bottom:5%; margin-left:22%; margin-right:22%; display:block;"><b>NUEVA PARTIDA</b></a>
                </div>
                <div class="col align-self-center">
                    <a class="btn btn-default btn-lg" href="/invitationsGame"
                        style="margin-top:4%; margin-bottom:4%; margin-left:22%; margin-right:22%; display:block;"><b>INVITACIONES A PARTIDA</b></a>
                </div>
                 
            </div>
        </sec:authorize>

</petclinic:layout>
