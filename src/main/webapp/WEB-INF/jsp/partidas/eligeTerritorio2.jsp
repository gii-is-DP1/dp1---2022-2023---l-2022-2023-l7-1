<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="CREAR PARTIDA">
    
    <head>
        <style>
           /* body {
              background-image: url('resources/images/tablero.png');
              background-repeat: no-repeat;
              background-position: center;
            }*/
            </style>
    </head>
    <body>
        <h2><c:out value="${now}"/></h2>
        
        <petclinic:mapa> 
            <c:forEach items= "${acciones}" var= "accion">
                <petclinic:territorio accion="${accion}"/>
            </c:forEach>    
        </petclinic:mapa>

        <h2>ELIGE TERRITORIO 2</h2>
        <form:form modelAttribute="turno" class="form-horizontal">
        <div class="form-select">
             <form:select path = "numTerritoriosJ1">
                <form:options items = "${dados}" />
             </form:select>
             <button class="btn btn-default" type="submit">Confirm</button> 
        </div>
    </form:form>

    <p>Criterio A1: <c:out value="${criterios[0]}"/></p>
    <p>Criterio A2: <c:out value="${criterios[1]}"/></p>
    <p>Criterio B1: <c:out value="${criterios[2]}"/></p>
    <p>Criterio B2: <c:out value="${criterios[3]}"/></p>
    
    </body>
    
</petclinic:layout>