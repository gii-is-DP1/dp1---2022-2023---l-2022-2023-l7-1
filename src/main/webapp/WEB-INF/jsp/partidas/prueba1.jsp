<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">
    <h1><em><b><u>Friends to Play</u></b></em></h1>
    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="text-align:center;">Username</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${friends}" var="friend">
            <tr>
                <td style="text-align:center;">                    
                    <c:out value="${friend.username}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="col align-self-center">
        <a class="btn btn-default btn-lg" href="/partida/crearPartidaMulti"
            style="margin-top:11%; margin-bottom:10%; margin-left:22%; margin-right:22%; display:block;"><b>CONFIRM INVITATIONS</b></a>
    </div>

</petclinic:layout>