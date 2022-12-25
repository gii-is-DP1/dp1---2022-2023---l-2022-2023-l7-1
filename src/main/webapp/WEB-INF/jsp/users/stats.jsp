<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="stats">
    <h2>Stats</h2>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th>Username</th>
            <th>Partidas Jugadas</th>
            <th>Partidas Ganadas</th>
            <th>Win Ratio</th>
            <th>Puntos Totales</th>
            <th>Maxima puntuacion</th>
            <th>Veces usadas poder ?</th>
            <th>Veces usadas poder +-1</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>
                    <c:out value="${user.username}"/>
                </td>
                <td>
                    <c:out value="${user.matchesPlayed}"/>
                </td>
                <td>
                    <c:out value="${user.gamesWin}"/>
                </td>
                <td>
                    <c:out value="${user.winRatio}"/>
                </td>
                <td>
                    <c:out value="${user.totalPoints}"/>
                </td>
                <td>
                    <c:out value="${user.maxPoints}"/>
                </td>
                <td>
                    <c:out value="${user.timesUsedPowerQuestion}"/>
                </td>
                <td>
                    <c:out value="${user.timesUsedPower1}"/>
                </td>


            </tr>
        </c:forEach>
        </tbody>
    </table>    
</petclinic:layout>