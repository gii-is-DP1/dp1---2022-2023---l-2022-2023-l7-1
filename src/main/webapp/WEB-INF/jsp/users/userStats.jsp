<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="User Stats">
    <h2>User Stats</h2>

    <table class="table table-striped">
        <tr>
            <th>Partidas Jugadas</th>
            <td><b><c:out value="${user.matchesPlayed}"/></b></td>
        </tr>
        <tr>
            <th>Partidas Ganadas</th>
            <td><b><c:out value="${user.gamesWin}"/></b></td>
        </tr>
        <tr>
            <th>WinRatio</th>
            <td><b><c:out value="${user.winRatio}"/></b></td>
        </tr>
        <tr>
            <th>Puntos Totales</th>
            <td><b><c:out value="${user.totalPoints}"/></b></td>
        </tr>
        <tr>
            <th>Maxima Puntuación</th>
            <td><b><c:out value="${user.maxPoints}"/></b></td>
        </tr>
        <tr>
            <th>Veces usadas poder ?</th>
            <td><b><c:out value="${user.timesUsedPowerQuestion}"/></b></td>
        </tr>
        <tr>
            <th>Veces usadas poder +-1</th>
            <td><b><c:out value="${user.timesUsedPower1}"/></b></td>
        </tr>
       
    </table>    
</petclinic:layout>