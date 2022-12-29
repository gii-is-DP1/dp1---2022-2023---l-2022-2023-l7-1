<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="CREAR PARTIDA">

    <body>
        <h2><c:out value="${now}"/></h2>
        
        <petclinic:mapa> 
            <c:forEach items= "${acciones}" var= "accion">
                <petclinic:territorio accion="${accion}"/>
            </c:forEach>    
        </petclinic:mapa>

        
        <h2>DIBUJAR</h2>
        <form:form   modelAttribute="action">
            <form:select path = "casilla">
                <form:options items = "${casillas}" />
            </form:select>
            <c:if test = "${tablero.poder1 > 0}">
                <form:form   modelAttribute="turno">
                    <form:select path = "numTerritoriosJ1">
                        <form:options items = "${poder}" />
                    </form:select>
                    <button class="btn btn-default" type="submit">Confirm</button> 
                </form:form>                 
             </c:if>
            <button class="btn btn-default" type="submit">Confirm</button> 
                
        </form:form>

        <p>Criterio A1: <c:out value="${criterios[0]}"/></p>
        <p>Criterio A2: <c:out value="${criterios[1]}"/></p>
        <p>Criterio B1: <c:out value="${criterios[2]}"/></p>
        <p>Criterio B2: <c:out value="${criterios[3]}"/></p>
                     
    </body>
    
</petclinic:layout>