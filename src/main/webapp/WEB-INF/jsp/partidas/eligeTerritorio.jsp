<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>


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

        <div class = "row">
            <div class="col-sm-3">
        <c:if test="${eligeTerritorio == true}">
            <h3>Choose territory and number of territories:</h3>
         </c:if>
         <c:if test="${eligeTerritorio == false}">
            <h3>Choose number of territories:</h3>
            <small>The number you choose is the number of territories to draw, the one you don't choose is the territory you will draw</small>
         </c:if>
        </div>
            <div class="col-sm-3">
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
            <div class="col-sm-3">
                <table id="criterios" class="table table-condensed table-bordered">
                   <thead>
                        <tr>
                            <th style="text-align:center">Criterion</th>
                            <th style="text-align:center">Number</th>
                        </tr>
                   </thead>
                   <tbody>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">A1</td>
                            <td style="text-align:center"><c:out value="${criterios[0]}"/></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">A2</td>
                            <td style="text-align:center"><c:out value="${criterios[1]}"/></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">B1</td>
                            <td style="text-align:center"><c:out value="${criterios[2]}"/></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">B2</td>
                            <td style="text-align:center"><c:out value="${criterios[3]}"/></td>
                        </tr>
                   </tbody>
                </table>
            </div>
            <div class="col-sm-3">
                <table id="territorios" class="table table-condensed table-bordered">
                    <thead>
                        <tr style="background-color:gainsboro">
                            <th style="text-align:center">Dice number</th>
                            <th>Territory</th>
                            <th style="text-align:center">Uses</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">- 1 -</td>
                            <td>Forest</td>
                            <td style="text-align:center"><b><c:out value="${usos[0]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">- 2 -</td>
                            <td>Castle</td>
                            <td style="text-align:center"><b><c:out value="${usos[1]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">- 3 -</td>
                            <td>Mountain</td>
                            <td style="text-align:center"><b><c:out value="${usos[2]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">- 4 -</td>
                            <td>Village</td>
                            <td style="text-align:center"><b><c:out value="${usos[3]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">- 5 -</td>
                            <td>Meadow</td>
                            <td style="text-align:center"><b><c:out value="${usos[4]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">- 6 -</td>
                            <td>River</td>
                            <td style="text-align:center"><b><c:out value="${usos[5]}"/></b></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <%-- Para poder2 --%>
            <c:if test = "${tablero.poder2 > 0}">
                               
            <c:out value="${tablero.poder2}"/>
             </c:if>
        </div>    
    </body>
    
</petclinic:layout>
