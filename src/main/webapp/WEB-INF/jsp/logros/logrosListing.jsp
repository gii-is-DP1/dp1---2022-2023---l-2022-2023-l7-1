<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">
    <h1><em><b><u>Achievements</u></b></em></h1>

    <table id="logroTable" class="table table-striped">
        <thead>
        <tr>
            <th  style="text-align:center">Title</th>
            <th  style="text-align:center">Description</th>
            <th  style="text-align:center">Logo</th>
            <th  style="text-align:center">Edit</th>
            <th  style="text-align:center">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${logros}" var="logro">
            <tr>
                <td style="text-align:center">                    
                    <c:if test="${logro.logo == ''}">none</c:if>
                    <c:if test="${logro.logo != ''}">
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
        style="margin-top:0%; margin-bottom:5%; margin-left:22%; margin-right:22%; display:block;"><b>NEW ACHIEVEMENT</b></a>

</petclinic:layout>