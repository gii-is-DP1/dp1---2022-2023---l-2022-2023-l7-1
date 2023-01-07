<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="userStats">
    <h1><em><b><u>Mis estadísticas</u></b></em></h1>
    <br>
    <table class="table table-striped">
        <tr>
            <th>Partidas jugadas</th>
            <td><b><c:out value="${user.matchesPlayed}"/></b></td>
        </tr>
        <tr>
            <th>Partidas ganadas</th>
            <td><b><c:out value="${user.gamesWin}"/></b></td>
        </tr>
        <tr>
            <th>Porcentaje victoria</th>
            <td><b><c:out value="${user.winRatio}"/></b></td>
        </tr>
        <tr>
            <th>Puntos totales</th>
            <td><b><c:out value="${user.totalPoints}"/></b></td>
        </tr>
        <tr>
            <th>Puntuaje máximo</th>
            <td><b><c:out value="${user.maxPoints}"/></b></td>
        </tr>
        <tr>

            <th>Veces usado Bosque</th>
            <td><b><c:out value="${user.timesUsedTerritory1}"/></b></td>
        </tr>
        <tr>
            <th>Veces usado Castillo</th>
            <td><b><c:out value="${user.timesUsedTerritory2}"/></b></td>
        </tr>
        <tr>
            <th>Veces usado Montana</th>
            <td><b><c:out value="${user.timesUsedTerritory3}"/></b></td>
        </tr>
        <tr>
            <th>Veces usado Poblado</th>
            <td><b><c:out value="${user.timesUsedTerritory4}"/></b></td>
        </tr>
        <tr>
            <th>Veces usado Pradera</th>
            <td><b><c:out value="${user.timesUsedTerritory5}"/></b></td>
        </tr>
        <tr>
            <th>Veces usado Rio</th>
            <td><b><c:out value="${user.timesUsedTerritory6}"/></b></td>

        </tr>
    </table>    
</petclinic:layout>