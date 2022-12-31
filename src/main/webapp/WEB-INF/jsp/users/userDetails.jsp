<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">

    <h1><em><b><u>User Information</u></b></em></h1>

    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${user.name}"/></b></td>
        </tr>
        <tr>
            <th>Last Name</th>
            <td><c:out value="${user.lastName}"/></td>
        </tr>
        <tr>
            <th>User Name</th>
            <td><c:out value="${user.username}"/></td>
        </tr>
        <tr>
            <th>Birthdate</th>
            <td><c:out value="${user.birthDate}"/></td>
        </tr>
        <tr>
            <th>Mail</th>
            <td><c:out value="${user.email}"/></td>
        </tr>
        <tr>
            <th>Telephone</th>
            <td><c:out value="${user.phone}"/></td>
        </tr>
        <tr>
            <th>Total Games</th>
            <td><c:out value="${user.matchesPlayed}"/></td>
        </tr>
    </table>

    <tr> 
        <td>
            <a class="btn btn-default" href="/friends/${user.username}">Friends</a>
        </td>  
    </tr>

    <tr> 
        <td>
            <a class="btn btn-default" href="/invitations/${user.username}">Invitations</a>
        </td>  
    </tr>

    <tr> 
        <td>
            <a class="btn btn-default" href="/users/${user.username}/userEdit">Edit User</a>
        </td>  
    </tr>

    <tr> 
        <td>
            <a class="btn btn-default" href="/partidas/${user.username}">Games User</a>
        </td>  
    </tr>

    <tr> 
        <td>
            <a class="btn btn-default" href="/users/logros/${user.username}">User Achievements</a>
        </td>  
    </tr>
    

</petclinic:layout>