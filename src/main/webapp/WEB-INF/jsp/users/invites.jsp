<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">
    
    <h1><em><b><u>Available Users</u></b></em></h1>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="text-align:center;">First Name</th>
            <th style="text-align:center;">Last Name</th>
            <th style="text-align:center;">Username</th>
            <th style="text-align:center;">Invite</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${availableList}" var="user">
                <tr>
                    <td style="text-align:center;">
                        <c:out value="${user.name}"/>
                    </td>
                    <td style="text-align:center;">                    
                        <c:out value="${user.lastName} "/>                                        
                    </td>
                    <td style="text-align:center;">                    
                        <c:out value="${user.username}"/>
                    </td>
                    <td style="text-align:center;"> 
                        <a href="/invitate/${username}/${user.username}"> 
                            <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>                            
                        </a>       
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    
    </petclinic:layout>