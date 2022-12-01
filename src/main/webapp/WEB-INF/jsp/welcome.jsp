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
                   <a class="btn btn-default" href="${perfil}">VER PERFIL</a>
                    </sec:authorize>
                </div>
                <spring:url value="users/{username}/stats" htmlEscape="true" var="stats">
                    <spring:param name="username" value="${username}" />
                </spring:url>
                <div class="col-sm-offset-2 col-sm-10">
                    <sec:authorize access="hasAnyAuthority('admin','player')">
                    <a class="btn btn-default" href="${stats}">VER ESTADISTICAS</a>
                    </sec:authorize>
                 </div>
                 <spring:url value="{username}/crearPartida" htmlEscape="true" var="crear">
                    <spring:param name="username" value="${username}" />
                 </spring:url>
                 <div class="col-sm-offset-2 col-sm-10">
                    <a class="btn btn-default" href="${crear}">CREAR PARTIDA</a>
                 </div>
            </div>
        </sec:authorize>
        </div>
        <div class="row">
            <div class="col-md-12">
            </div>
        </div>
</petclinic:layout>
