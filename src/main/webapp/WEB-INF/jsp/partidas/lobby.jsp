<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">
    <h2>Available Users</h2>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th>First Name</th>
            <th>LastName</th>
            <th>Username</th>
            <th>Invite</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${availableList}" var="user">
                <tr>
                    <td>
                        <c:out value="${user.name}"/>
                    </td>
                    <td>                    
                        <c:out value="${user.lastName} "/>                                        
                    </td>
                    <td>                    
                        <c:out value="${user.username}"/>
                    </td>
                    <td> 
                        <a href="/users/${username}/invitate/${user.username}"> 
                            <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>                            
                        </a>       
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    
    </petclinic:layout>