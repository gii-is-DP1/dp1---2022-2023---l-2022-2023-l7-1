<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="sala de Espera">
    <head> 
        <meta http-equiv="refresh" content="3"> 
    </head>

    <h1 style="text-align: center;"><em><b><u>LISTADO DE JUGADORES EN ESPERA</u></b></em></h1>
<table id="usersTable" class="table table-striped">
    <thead>
    <tr>
        <th style="text-align:center;">JUGADORES</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${jugadoresAceptados}" var="friend">
        <tr>
                <td>
                    <c:out value="${friend.username}"/>
                </td>
            </tr>
            </c:forEach>
    </tbody>
</table>
<h2 style="text-align: center;"><b>Esperando al anfitrion</b></h2>
<h2 style="text-align: center;"><b>La partida comenzará en breve...</b></h2>

</petclinic:layout>