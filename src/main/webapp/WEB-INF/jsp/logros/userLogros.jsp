<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="logros User">
    <h1><em><b><u>Mis Logros</u></b></em></h1>

    <table id="logroTable" class="table table-striped">
        <thead>
        <tr>
            <th  style="text-align:center">Logo</th>
            <th  style="text-align:center">Título</th>
            <th  style="text-align:center">Descripción</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${logrosUser}" var="logro">
            <tr>
                <td style="text-align:center">                    
                    <c:if test="${logro.logo == ''}">none</c:if>
                    <c:if test="${logro.logo != ''}">
                        <img src="../../../../resources/images/${logro.logo}" width="50px"  /> 

                    </c:if>
                </td>
                <td style="text-align:center">
                    <c:out value="${logro.titulo}"/>
                </td>
                <td style="text-align:center">                    
                    <c:out value="${logro.reqDescripcion} "/>                                        
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
