<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="stats">
    <h1><em><b><u>Estadísticas</u></b></em></h1>

    <table id="usersTable" class="table table-striped" style="width: 100%;">
        <thead>
        <tr>
            <th style="text-align:center">Usuario</th>
            <th style="text-align:center">Partidas jugadas</th>
            <th style="text-align:center">Partidas ganadas</th>
            <th style="text-align:center">Porcentaje de victoria</th>
            <th style="text-align:center">Puntos totales</th>
            <th style="text-align:center">Puntuaje máximo</th>
            <th style="text-align:center">Veces usado Bosque</th>
            <th style="text-align:center">Veces usado Castillo</th>
            <th style="text-align:center">Veces usado Montana</th>
            <th style="text-align:center">Veces usado Poblado</th>
            <th style="text-align:center">Veces usado Prader</th>
            <th style="text-align:center">Veces usado Rio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td style="text-align:center; width: 200px;">
                    <c:out value="${user.username}"/>
                </td>
                <td style="text-align:center; width: 200px;">
                    <c:out value="${user.matchesPlayed}"/>
                </td>
                <td style="text-align:center; width: 200px;">
                    <c:out value="${user.gamesWin}"/>
                </td>
                <td style="text-align:center; width: 200px;">
                    <c:out value="${user.winRatio}"/>
                </td>
                <td style="text-align:center; width: 200px;">
                    <c:out value="${user.totalPoints}"/>
                </td>
                <td style="text-align:center; width: 200px;">
                    <c:out value="${user.maxPoints}"/>
                </td>
                <td style="text-align:center; width: 200px;">
                    <c:out value="${user.timesUsedTerritory1}"/>
                </td>
                <td style="text-align:center; width: 200px;">
                    <c:out value="${user.timesUsedTerritory2}"/>
                </td>
                <td style="text-align:center; width: 200px;">
                    <c:out value="${user.timesUsedTerritory3}"/>
                </td>
                <td style="text-align:center; width: 200px;">
                    <c:out value="${user.timesUsedTerritory4}"/>
                </td>
                <td style="text-align:center; width: 200px;">
                    <c:out value="${user.timesUsedTerritory5}"/>
                </td>
                <td style="text-align:center; width: 200px;">
                    <c:out value="${user.timesUsedTerritory6}"/>
                </td>


            </tr>
        </c:forEach>
        </tbody>
    </table>   
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <nav aria-label="Pagination" style="text-align: center;">
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <c:if test ="${prev != 0}"><a href="/stats?page=${prev}">Anterior</a> </c:if>
                    </li>
                    <li class="page-item">
                    <c:forEach items="${pages}" var="page">
                        <a href="/stats?page=${page}"> <c:out value="${page}"/></a>
                    </c:forEach>
                    </li>
                    <li class="page-item">
                        <c:if test ="${next != last+1}"> <a href="/stats?page=${next}">Siguiente</a> </c:if>
                    </li>
                </ul>
            </nav>
        </div>
        
    </div> 
</petclinic:layout>