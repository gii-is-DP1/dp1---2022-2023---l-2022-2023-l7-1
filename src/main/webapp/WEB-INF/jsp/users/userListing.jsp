<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">
    <h2>User Listing</h2>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th>First Name</th>
            <th>LastName</th>
            <th>Brith Date</th>
            <th>Email</th>
            <th>Telephone</th>
            <th>Username</th>
            <th>Password</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>
                    <c:out value="${user.name}"/>
                </td>
                <td>                    
                    <c:out value="${user.lastName} "/>                                        
                </td>
                <td>                    
                    <c:out value="${user.birthDate}"/>
                </td>
                <td>                    
                    <c:out value="${user.email}"/>
                </td>
                <td>                    
                    <c:out value="${user.phone}"/>
                </td>
                <td>                    
                    <c:out value="${user.username}"/>
                </td>
                <td>                    
                    <c:out value="${user.password}"/>
                </td>
                <td> 
                    <a href="/users/${user.username}/edit"> 
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>                            
                    </a>       
                </td>
                <td> 
                    <a href="/users/${user.username}/delete"> 
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>      
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>
