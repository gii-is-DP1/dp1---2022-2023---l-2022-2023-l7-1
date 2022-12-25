<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="usersA">
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
                    <c:forEach items="${user.authorities}" var="uauthorities">
                        <c:if test ="${uauthorities.authority != 'admin'}"> 
                        <a href="/users/${user.username}/edit"> 
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>                            
                        </a>       
                        </c:if>
                    </c:forEach>
                </td>
                <td> 
                    <c:forEach items="${user.authorities}" var="uauthorities">
                        <c:if test ="${uauthorities.authority != 'admin'}"> 
                            <a href="/users/${user.username}/delete"> 
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                            </a>       
                        </c:if>
                    </c:forEach> 
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <nav aria-label="Pagination">
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <c:if test ="${prev != 0}"><a href="/users/all?page=${prev}">Anterior</a> </c:if>
                    </li>
                    <li class="page-item">
                    <c:forEach items="${pages}" var="page">
                        <a href="/users/all?page=${page}"> <c:out value="${page}"/></a>
                    </c:forEach>
                    </li>
                    <li class="page-item">
                        <c:if test ="${next != last+1}"> <a href="/users/all?page=${next}">Siguiente</a> </c:if>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="col-md-2"></div>
    </div>

</petclinic:layout>
