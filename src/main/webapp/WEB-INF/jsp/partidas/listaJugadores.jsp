<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="users">
    <h1><em><b><u>Amigos para jugar</u></b></em></h1>
    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="text-align:center;">Usuario</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadoresAceptados}" var="player">
            <tr>
                <td style="text-align:center;">                    
                    <c:out value="${player.username}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>