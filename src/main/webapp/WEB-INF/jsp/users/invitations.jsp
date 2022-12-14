<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="invitations">
    <h1><em><b><u>Invitations</u></b></em></h1>

    <table id="invitationsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Enviada</th>
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
                    <a class="btn btn-default" href="/invitationAccepted/${invitation.receiver.username}/${invitation.id}">Aceptar invitación</a>
                    <a class="btn btn-default" href="/invitationCancelled/${invitation.receiver.username}/${invitation.id}">Cancelar invitación</a>
                </td> 
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <tr> 
        <td>
            <a class="btn btn-default" href="/invite/${username}">Crear invitación</a>
        </td>  
    </tr>

</petclinic:layout>