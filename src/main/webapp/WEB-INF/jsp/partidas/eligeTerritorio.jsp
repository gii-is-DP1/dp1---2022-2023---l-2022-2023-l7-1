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

        <h2>ELIGE TERRITORIO</h2>
        <div class = "row">
            <div class="col-sm-4">
        <form:form modelAttribute="turno" class="form-horizontal">
        <div class="form-select">
            <c:if test="${eligeTerritorio}">
                <form:select path = "territorio">
                    <form:options items = "${territorios}" />
                </form:select>
            </c:if>
            <form:select path = "numTerritoriosJ1">
                <form:options items = "${dados}" />
            </form:select>
            <div class = "row">
            <button class="btn btn-default" type="submit">Confirm</button> 
            </div>
        </div>
    </form:form>
            </div>
    <div class="col-sm-4">
        <p>Criterio A1: <c:out value="${criterios[0]}"/></p>
        <p>Criterio A2: <c:out value="${criterios[1]}"/></p>
        <p>Criterio B1: <c:out value="${criterios[2]}"/></p>
        <p>Criterio B2: <c:out value="${criterios[3]}"/></p>
    </div>
        <div class="col-sm-4">
        <p>Usos Bosque (1): <c:out value="${usos[0]}"/></p>
        <p>Usos Castillo (2): <c:out value="${usos[1]}"/></p>
        <p>Usos Montana (3): <c:out value="${usos[2]}"/></p>
        <p>Usos Poblado (4): <c:out value="${usos[3]}"/></p>
        <p>Usos Pradera (5): <c:out value="${usos[4]}"/></p>
        <p>Usos Rio (6): <c:out value="${usos[5]}"/></p>
        </div>
    </div>    
    </body>
    
</petclinic:layout>
