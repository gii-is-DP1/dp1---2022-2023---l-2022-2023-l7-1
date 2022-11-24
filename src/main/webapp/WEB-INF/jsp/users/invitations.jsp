<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="invitations">
    <table id="invitationsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Sender</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${invitationsList}" var="invitation">
            <tr>
                <td>
                    <c:out value="${invitation.sender.username}"/>
                </td> 
                <td style="text-align:right">
                    <a class="btn btn-default" href="/users/${invitation.receiver.username}/accept/${invitation.id}">Accept Invitation</a>
                    <a class="btn btn-default" href="/users/${invitation.receiver.username}/cancelInvite/${invitation.id}">Cancel Invitation</a>
                </td> 
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <tr> 
        <td>
            <a class="btn btn-default" href="/users/${username}/invite">Create invitation</a>
        </td>  
    </tr>

</petclinic:layout>