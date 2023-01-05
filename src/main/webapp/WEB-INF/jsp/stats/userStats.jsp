<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="userStats">
    <h1><em><b><u>My Stats</u></b></em></h1>
    <br>
    <table class="table table-striped">
        <tr>
            <th>Games Played</th>
            <td><b><c:out value="${user.matchesPlayed}"/></b></td>
        </tr>
        <tr>
            <th>Games Winned</th>
            <td><b><c:out value="${user.gamesWin}"/></b></td>
        </tr>
        <tr>
            <th>Win Ratio</th>
            <td><b><c:out value="${user.winRatio}"/></b></td>
        </tr>
        <tr>
            <th>All Points</th>
            <td><b><c:out value="${user.totalPoints}"/></b></td>
        </tr>
        <tr>
            <th>Max Score</th>
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