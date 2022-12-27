<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="stats">
    <h1><em><b><u>Stats</u></b></em></h1>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="text-align:center">Username</th>
            <th style="text-align:center">Games Played</th>
            <th style="text-align:center">Games Winned</th>
            <th style="text-align:center">Win Ratio</th>
            <th style="text-align:center">All Points</th>
            <th style="text-align:center">Max Score</th>
            <th style="text-align:center">Time used <em>?</em> power</th>
            <th style="text-align:center">Time used <em>+-1</em> power</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td  style="text-align:center">
                    <c:out value="${user.username}"/>
                </td>
                <td  style="text-align:center">
                    <c:out value="${user.matchesPlayed}"/>
                </td>
                <td  style="text-align:center">
                    <c:out value="${user.gamesWin}"/>
                </td>
                <td  style="text-align:center">
                    <c:out value="${user.winRatio}"/>
                </td>
                <td  style="text-align:center">
                    <c:out value="${user.totalPoints}"/>
                </td>
                <td  style="text-align:center">
                    <c:out value="${user.maxPoints}"/>
                </td>
                <td  style="text-align:center">
                    <c:out value="${user.timesUsedPowerQuestion}"/>
                </td>
                <td  style="text-align:center">
                    <c:out value="${user.timesUsedPower1}"/>
                </td>


            </tr>
        </c:forEach>
        </tbody>
    </table>    
</petclinic:layout>