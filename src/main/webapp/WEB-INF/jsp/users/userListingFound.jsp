<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="usersA">
    <h1><em><b><u>Listado de usuarios</u></b></em></h1>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th  style="text-align:center">Nombre</th>
            <th  style="text-align:center">Apellidos</th>
            <th  style="text-align:center">Cumpleaños</th>
            <th  style="text-align:center">Correo</th>
            <th  style="text-align:center">Teléfono</th>
            <th  style="text-align:center">Usuasio</th>
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
