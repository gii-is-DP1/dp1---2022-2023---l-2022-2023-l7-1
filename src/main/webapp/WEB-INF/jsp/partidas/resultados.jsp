<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="RESULTS">

    <body>
        <h2><c:out value="${now}"/></h2>
        
        <petclinic:mapa> 
            <c:forEach items= "${acciones}" var= "accion">
                <petclinic:territorio accion="${accion}"/>
            </c:forEach>    
        </petclinic:mapa>

        <h2>GAME RESULTS</h2>
        <div class = "row">
            <h2><c:out value="${puntos}"/></h2>

            <div class="col-sm-4">
                <p>Criterio A1: <c:out value="${criterios[0]}"/></p>
                <p>Criterio A2: <c:out value="${criterios[1]}"/></p>
                <p>Criterio B1: <c:out value="${criterios[2]}"/></p>
                <p>Criterio B2: <c:out value="${criterios[3]}"/></p>
            </div>


    </body>
    
</petclinic:layout>