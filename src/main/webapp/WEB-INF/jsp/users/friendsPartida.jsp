<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">

    <h1><em><b><u>Partida Amigos</u></b></em></h1>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="text-align:center;">Nombre</th>
            <th style="text-align:center;">Apellidos</th>
            <th style="text-align:center;">Usuario</th>
            <th style="text-align:center;">Partida En Curso</th>
            <th style="text-align:center;">Ver Partida</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tableros}" var="tablero">
            <tr>
                <td style="text-align:center;">
                    <c:out value="${tablero.user.name}"/>
                </td>
                <td style="text-align:center;">                    
                    <c:out value="${tablero.user.lastName} "/>                                        
                </td>
                <td style="text-align:center;">                    
                    <c:out value="${tablero.user.username}"/>
                </td>
                <td style="text-align:center;">                    
                    <c:out value="${tablero.partidaEnCurso}"/>
                </td>
                <td style="text-align:center;"> 
                    <a href="/partidas/partidaEnCurso/${tablero.user.username}/${tablero.id}"> 
                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                    </a>      
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>