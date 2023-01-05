<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<style>
    body {
        background-image: url("/resources/images/FondoPartida.png");
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: 100% 100%;
    }
</style>

<petclinic:layout pageName="CREAR PARTIDA">
    
    <body>
        <h2><c:out value="${now}"/></h2>
        
        <petclinic:mapa> 
            <c:forEach items= "${acciones}" var= "accion">
                <petclinic:territorio accion="${accion}"/>
            </c:forEach>    
        </petclinic:mapa>
        
        
        <h2>Dibujar</h2>
        <div class = "row">

        
        <form:form   modelAttribute="action">
            <div class="col-sm-4">
                <div class="col-sm-7">
                    <h3>Elige casilla para dibujar:</h3>
                    <h3>Casillas pendientes a dibujar: <c:out value="${porDibujar}"/></h3>
           
                </div>
                
            <form:select path = "casilla">    
                <form:options items = "${casillas}" />
            </form:select>
        </div>
        
            <c:if test = "${tablero.poder1 > 0}">
                <div class="col-sm-4">
                <form:form   modelAttribute="turno">
                    <h3>Poder +-1:</h3>
                    <form:select path = "numTerritoriosJ1">
                        <form:options items = "${poder}" />
                    </form:select>
                    <h3>Usos: <c:out value="${poder1}"/></h3>
                    <div class = "row">
                    <button class="btn btn-default" type="submit">Confirmar</button> 
                    </div>
                </div>
                </form:form>                 
                
             </c:if>
           
                
             <c:if test = "${tablero.poder1 == 0}">
                <div class="row">
                        <button class="btn btn-default" type="submit">Confirmar</button> 
                </div>
             </c:if>
            
        </form:form>
        

        <div class="col-sm-2">
            <table id="criterios" class="table table-condensed table-bordered">
               <thead>
                    <tr>
                        <th style="text-align:center">Criterio</th>
                    </tr>
               </thead>
               <tbody>
                    <tr style="background-color:gainsboro">
                        <td style="text-align:center">A<c:out value="${criterios[0]}"/></td>
                    </tr>
                    <tr style="background-color:gainsboro">
                        <td style="text-align:center">A<c:out value="${criterios[1]}"/></td>
                    </tr>
                    <tr style="background-color:gainsboro">
                        <td style="text-align:center">B<c:out value="${criterios[2]}"/></td>
                    </tr>
                    <tr style="background-color:gainsboro">
                        <td style="text-align:center">B<c:out value="${criterios[3]}"/></td>
                    </tr>
               </tbody>
            </table>
        </div>
    </div>
    <!-- Para poder2 -->
         <c:if test = "${tablero.poder2 > 0}">
                               
            <c:out value="${tablero.poder2}"/>
            </c:if>
    </body>
    
</petclinic:layout>