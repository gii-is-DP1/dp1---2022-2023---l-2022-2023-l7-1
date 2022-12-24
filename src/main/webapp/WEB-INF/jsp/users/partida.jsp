<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">
    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th>Id de partida</th>
            <th>partidaEnCurso</th>
            <th>puntos</th>
            <th>Username</th>
            <th>Partida Creada</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tablero}" var="tablero">
            <tr>
                <td>
                    <c:out value="${tablero.partida.id}"/>
                </td>
                <td>                    
                    <c:out value="${tablero.partidaEnCurso} "/>                                        
                </td>
                <td>                    
                    <c:out value="${tablero.puntos}"/>
                </td>
                <td>                    
                    <c:out value="${tablero.user.username}"/>
                </td>
                <td>                    
                    <c:out value="${tablero.partidaCreada}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>