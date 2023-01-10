<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="invitations">
    <h1><em><b><u>Invitaciones a Partida</u></b></em></h1>

    <table id="invitationsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Recibidas</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${invitationToPlay}" var="invitationGame">
            <tr>
                <td>
                    <c:out value="${invitationGame.anfitrion.username}"/>
                </td> 
                <td style="text-align:right">
                    <a class="btn btn-default" href="/invitationToGameAccepted/${invitationGame.id}">Unirme a partida</a>
                    <a class="btn btn-default" href="/invitationToGameCancelled/${invitationGame.id}">Cancelar invitación</a>
                </td> 
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>