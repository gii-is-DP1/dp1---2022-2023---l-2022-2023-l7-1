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
            <th>Time used <em>?</em> power</th>
            <td><b><c:out value="${user.timesUsedPowerQuestion}"/></b></td>
        </tr>
        <tr>
            <th>Time used <em>+-1</em> power</th>
            <td><b><c:out value="${user.timesUsedPower1}"/></b></td>
        </tr>
       
    </table>    
</petclinic:layout>