<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="users">
    <h1><em><b><u>Invita a tus amigos para jugar</u></b></em></h1>

    <div class="col align-self-center">
        <table id="usersTable" class="table table-striped">
            <thead>
            <tr>
                <th style="text-align:center;">Usuario</th>
                <th style="text-align:center;">Invitar a jugar</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${friendsToPlay}" var="friend">
                
                <tr>
                    <td style="text-align:center;">                    
                        <c:out value="${friend.username}"/>
                    </td>
                    <td style="text-align:center;"> 
                        <a href="/invitateToPlay/${friend.username}"> 
                            <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>                            
                        </a>       
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="col align-self-center">
        <a class="btn btn-default btn-lg" href="/salaDeEspera"
            style="margin-top:11%; margin-bottom:10%; margin-left:22%; margin-right:22%; display:block;"><b>Confirmar invitación</b></a>
    </div>

</petclinic:layout>