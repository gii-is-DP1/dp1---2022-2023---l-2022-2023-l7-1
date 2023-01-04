<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="users">

    <h1><em><b><u>Información de los usuarios</u></b></em></h1>

    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${user.name}"/></b></td>
        </tr>
        <tr>
            <th>Apellidos</th>
            <td><c:out value="${user.lastName}"/></td>
        </tr>
        <tr>
            <th>Usuario</th>
            <td><c:out value="${user.username}"/></td>
        </tr>
        <tr>
            <th>Cumpleaños</th>
            <td><c:out value="${user.birthDate}"/></td>
        </tr>
        <tr>
            <th>Correo</th>
            <td><c:out value="${user.email}"/></td>
        </tr>
        <tr>
            <th>Teléfono</th>
            <td><c:out value="${user.phone}"/></td>
        </tr>
        <tr>
            <th>Juegos totales</th>
            <td><c:out value="${user.matchesPlayed}"/></td>
        </tr>
    </table>

    <tr> 
        <td>
            <a class="btn btn-default" href="/friends/${user.username}">Amigos</a>
        </td>  
    </tr>

    <tr> 
        <td>
            <a class="btn btn-default" href="/invitations/${user.username}">Invitaciones</a>
        </td>  
    </tr>

    <tr> 
        <td>
            <a class="btn btn-default" href="/users/${user.username}/userEdit">Editar usuario</a>
        </td>  
    </tr>

    <tr> 
        <td>
            <a class="btn btn-default" href="/partidas/${user.username}">Juegos</a>
        </td>  
    </tr>

    <tr> 
        <td>
            <a class="btn btn-default" href="/users/logros/${user.username}">Mis logros</a>
        </td>  
    </tr>
    

</petclinic:layout>