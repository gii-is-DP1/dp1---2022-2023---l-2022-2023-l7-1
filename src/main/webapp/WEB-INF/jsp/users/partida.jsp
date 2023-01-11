<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="games">
    <h1><em><b><u>Juegos</u></b></em></h1>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="text-align:center">Identificación</th>
            <th style="text-align:center">Juego en proceso</th>
            <th style="text-align:center">Puntos</th>
            <th style="text-align:center">Nombre de usuario</th>
            <th style="text-align:center">Creador del juego</th>
            <th style="text-align:center">Borrar</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tablero}" var="tablero">
            <tr>
                <td style="text-align:center">
                    <c:out value="${tablero.partida.id}"/>
                </td>
                <td style="text-align:center">                    
                    <c:out value="${tablero.partidaEnCurso} "/>                                        
                </td>
                <td style="text-align:center">                    
                    <c:out value="${tablero.puntos}"/>
                </td>
                <td style="text-align:center">                    
                    <c:out value="${tablero.user.username}"/>
                </td>
                <td style="text-align:center">                    
                    <c:out value="${tablero.partidaCreada}"/>
                </td>
                <td style="text-align:center">
                    <a href="/partidas/${tablero.partida.id}/delete"> 
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>       
                </td>
            </tr>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>