<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">

    <h1><em><b><u>Amigos</u></b></em></h1>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="text-align:center;">Nombre</th>
            <th style="text-align:center;">Apellidos</th>
            <th style="text-align:center;">Usuario</th>
            <th style="text-align:center;"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${friends}" var="friend">
            <tr>
                <td style="text-align:center;">
                    <c:out value="${friend.name}"/>
                </td>
                <td style="text-align:center;">                    
                    <c:out value="${friend.lastName} "/>                                        
                </td>
                <td style="text-align:center;">                    
                    <c:out value="${friend.username}"/>
                </td>
                <td style="text-align:center;"> 
                    <a href="/friends/${friend.username}/delete"> 
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>      
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <b><a style="position: absolute; margin-left: 1075px; " href="/friends/partidas" class="btn btn-default">Ver partidas</a></b>

</petclinic:layout>