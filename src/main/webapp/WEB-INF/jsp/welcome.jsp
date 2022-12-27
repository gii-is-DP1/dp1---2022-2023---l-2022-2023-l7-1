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
        <div class="row">
            <sec:authorize access="isAuthenticated()">
            
            <div class="form-group">
                <spring:url value="users/{username}" htmlEscape="true" var="perfil">
                    <spring:param name="username" value="${username}" />
                </spring:url>
                <div class="col-sm-offset-2 col-sm-10">
                    <sec:authorize access="hasAnyAuthority('admin','player')">
                   <a class="btn btn-default" href="${perfil}">MY PROFILE</a>
                    </sec:authorize>
                </div>

                 <spring:url value="/partida/crearPartida" htmlEscape="true" var="crear">

                 </spring:url>
                 <div class="col-sm-offset-2 col-sm-10">
                    <a class="btn btn-default" href="${crear}">NEW GAME</a>
                 </div>
            </div>
        </sec:authorize>
        </div>
        <div class="row">
            <div class="col-md-12">
            </div>
        </div>
</petclinic:layout>
