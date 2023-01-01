<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    <h1><em><b><u>Games</u></b></em></h1>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="text-align:center">Identification</th>
            <th style="text-align:center">Game in process</th>
            <th style="text-align:center">Points</th>
            <th style="text-align:center">User name</th>
            <th style="text-align:center">Game Creator</th>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>