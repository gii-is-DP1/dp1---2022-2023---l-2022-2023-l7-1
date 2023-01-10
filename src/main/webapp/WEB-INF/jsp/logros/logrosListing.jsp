<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="achievements">
    <h1><em><b><u>Logros</u></b></em></h1>

    <table id="logroTable" class="table table-striped">
        <thead>
        <tr>
            <th  style="text-align:center">Logo</th>
            <th  style="text-align:center">Título</th>
            <th  style="text-align:center">Descripción</th>
            <th  style="text-align:center">Editar</th>
            <th  style="text-align:center">Borrar</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${logros}" var="logro">
            <tr>
                <td style="text-align:center">                    
                    <c:if test="${empty logro.logo}">Sin imagen :(</c:if>
                    <c:if test="${not empty logro.logo}">
                        <img src="resources/images/${logro.logo}" width="80px"  /> 
                    </c:if>
                </td>
                <td style="text-align:center">
                    <c:out value="${logro.titulo}"/>
                </td>
                <td style="text-align:center">                    
                    <c:out value="${logro.reqDescripcion} "/>                                        
                </td>
                <td style="text-align:center"> 
                    <a href="/logros/${logro.id}/edit"> 
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>                            
                    </a>       
                </td>
                <td style="text-align:center"> 
                    <a href="/logros/${logro.id}/delete"> 
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>      
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
        <a class="btn btn-default" href="/logros/new"
        style="margin-top:0%; margin-bottom:5%; margin-left:22%; margin-right:22%; display:block;"><b>NUEVO LOGRO</b></a>

</petclinic:layout>