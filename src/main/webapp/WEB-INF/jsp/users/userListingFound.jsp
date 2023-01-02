<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="usersA">
    <h1><em><b><u>User Listing</u></b></em></h1>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th  style="text-align:center">First Name</th>
            <th  style="text-align:center">LastName</th>
            <th  style="text-align:center">Brith Date</th>
            <th  style="text-align:center">Email</th>
            <th  style="text-align:center">Telephone</th>
            <th  style="text-align:center">Username</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td style="text-align:center">
                    <c:out value="${user.name}"/>
                </td>
                <td style="text-align:center">                    
                    <c:out value="${user.lastName} "/>                                        
                </td>
                <td style="text-align:center">                    
                    <c:out value="${user.birthDate}"/>
                </td>
                <td style="text-align:center">                    
                    <c:out value="${user.email}"/>
                </td>
                <td style="text-align:center">                    
                    <c:out value="${user.phone}"/>
                </td>
                <td style="text-align:center">                    
                    <c:out value="${user.username}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>
