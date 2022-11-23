<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">
    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th>First Name</th>
            <th>LastName</th>
            <th>Username</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${friends}" var="friend">
            <tr>
                <td>
                    <c:out value="${friend.name}"/>
                </td>
                <td>                    
                    <c:out value="${friend.lastName} "/>                                        
                </td>
                <td>                    
                    <c:out value="${friend.username}"/>
                </td>
                <td> 
                    <a href="/users/${user.username}/friends/${friend.username}/delete"> 
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>      
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>