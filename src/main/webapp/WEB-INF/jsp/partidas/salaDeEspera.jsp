<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="sala de Espera">
    <head> 
        <meta http-equiv="refresh" content="3"> 
    </head>

    <h1 style="text-align: center;"><em><b>LISTADO DE JUGADORES EN ESPERA</b></em></h1>
    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="text-align:center;">JUGADORES</th>
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
    <c:if test="${tamañoJugadores > 1}">
        <div class="col align-self-center">
            <a class="btn btn-default btn-lg" href="../partida/crearPartidaMultijugador"
                style="margin-top:11%; margin-bottom:5%; margin-left:22%; margin-right:22%; display:block;"><b>COMENZAR PARTIDA</b></a>
        </div>
    </c:if>
</petclinic:layout>