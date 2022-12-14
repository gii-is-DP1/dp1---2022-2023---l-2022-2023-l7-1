<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

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
            <th  style="text-align:center">Usuario</th>
            <th  style="text-align:center">Contraseña</th>
            <th  style="text-align:center">Editar</th>
            <th  style="text-align:center">Borrar</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td style="text-align:center">
                    <c:out value="${user.name}"/>
                </td>
                <td  style="text-align:center">                    
                    <c:out value="${user.lastName} "/>                                        
                </td>
                <td  style="text-align:center">                    
                    <c:out value="${user.birthDate}"/>
                </td>
                <td  style="text-align:center">                    
                    <c:out value="${user.email}"/>
                </td>
                <td  style="text-align:center">                    
                    <c:out value="${user.phone}"/>
                </td>
                <td  style="text-align:center">                    
                    <c:out value="${user.username}"/>
                </td>
                <td  style="text-align:center">                    
                    <c:out value="${user.password}"/>
                </td>
                <td  style="text-align:center"> 
                    <c:forEach items="${user.authorities}" var="uauthorities">
                        <c:if test ="${uauthorities.authority != 'admin'}"> 
                        <a href="/users/${user.username}/edit"> 
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>                            
                        </a>       
                        </c:if>
                    </c:forEach>
                </td>
                <td  style="text-align:center"> 
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
            <nav aria-label="Pagination" style="text-align: center;">
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
        
    </div>

</petclinic:layout>
