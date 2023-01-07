<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="sala de Espera">
<h1><em><b><u>Esperando al anfitrion</u></b></em></h1>
<table id="usersTable" class="table table-striped">
    <thead>
    <tr>
        <th style="text-align:center;">Anfitrion</th>
    </tr>
    </thead>
    <tbody>
        <tr>
        <td>
            <c:out value="${username}"/>
        </td>
    </tr>
    </tbody>
</table>
<table>
    <thead>
        <tr>
            <th style="text-align:center;">Invitados</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${jugadoresAceptados}" var="friend">
        <tr>
                <td>
                    <c:out value="${friend.username}"/>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</petclinic:layout>