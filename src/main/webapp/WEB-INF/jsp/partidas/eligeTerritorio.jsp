<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="CREAR PARTIDA">
    <h2>CREACION DE PARTIDAS</h2>
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

        
        
    </body>
    <form:form>
        <div class="col-sm-offset-2 col-sm-10">
            <form name = "SeleccionarTerritorio" ><select name="Territorios" size = "1">
                <c:forEach items="${territorios}" var="territorio" varStatus="loop">
                    <option value="${loop.index}">
                        ${territorio}
                    </option>
                </c:forEach>
            </select> 
            <button type="submit" class="btn btn-default">Seleccionar</button>  
            </form>
                
        </div>
    </form:form>

</petclinic:layout>
