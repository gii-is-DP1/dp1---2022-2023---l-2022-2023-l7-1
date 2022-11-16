<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">

    <h2>User Information</h2>
    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${user.name} ${user.lastName}"/></b></td>
        </tr>
        <tr>
            <th>User Name</th>
            <td><c:out value="${user.username}"/></td>
        </tr>
        <tr>
            <th>Birth Date</th>
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

    

</petclinic:layout>