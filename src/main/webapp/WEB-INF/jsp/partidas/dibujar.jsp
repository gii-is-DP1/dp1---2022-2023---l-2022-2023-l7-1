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
        <div class = "row">

        
        <form:form   modelAttribute="action">
            <div class="col-sm-2">
            <p>Casilla:</p>
            <form:select path = "casilla">    
                <form:options items = "${casillas}" />
            </form:select>
        </div>
        
            <c:if test = "${tablero.poder1 > 0}">
                <div class="col-sm-2">
                <form:form   modelAttribute="turno">
                    <p>Poder +-1:</p>
                    <p>Usos: <c:out value="${poder1}"/></p>
                    <form:select path = "numTerritoriosJ1">
                        <form:options items = "${poder}" />
                    </form:select>
                    <div class = "row">
                    <button class="btn btn-default" type="submit">Confirm</button> 
                    </div>
                </div>
                </form:form>                 
                
             </c:if>
            
             <c:if test = "${tablero.poder1 == 0}">
                <div class="row">
                        <button class="btn btn-default" type="submit">Confirm</button> 
                </div>
             </c:if>
            
        </form:form>
        

        <div class="col-sm-2">
        <p>Criterio A1: <c:out value="${criterios[0]}"/></p>
        <p>Criterio A2: <c:out value="${criterios[1]}"/></p>
        <p>Criterio B1: <c:out value="${criterios[2]}"/></p>
        <p>Criterio B2: <c:out value="${criterios[3]}"/></p>
        </div>
    </div>
    </body>
    
</petclinic:layout>