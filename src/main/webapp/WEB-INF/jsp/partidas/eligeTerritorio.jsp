<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="CREAR PARTIDA">

    <body>
        <h2><c:out value="${now}"/></h2>
        
        <petclinic:mapa> 
            <c:forEach items= "${acciones}" var= "accion">
                <petclinic:territorio accion="${accion}"/>
            </c:forEach>    
        </petclinic:mapa>

        <h2>Choose territory:</h2>

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
            <div class="col-sm-3">
                <table id="criterios" class="table table-striped table-condensed">
                   <thead>
                        <tr>
                            <th style="text-align:center">Criterion</th>
                            <th style="text-align:center">Number</th>
                        </tr>
                   </thead>
                   <tbody>
                        <tr>
                            <td style="text-align:center">A1</td>
                            <td style="text-align:center"><c:out value="${criterios[0]}"/></td>
                        </tr>
                        <tr>
                            <td style="text-align:center">A2</td>
                            <td style="text-align:center"><c:out value="${criterios[1]}"/></td>
                        </tr>
                        <tr>
                            <td style="text-align:center">B1</td>
                            <td style="text-align:center"><c:out value="${criterios[2]}"/></td>
                        </tr>
                        <tr>
                            <td style="text-align:center">B2</td>
                            <td style="text-align:center"><c:out value="${criterios[3]}"/></td>
                        </tr>
                   </tbody>
                </table>
            </div>
            <div class="col-sm-4">
                <table id="territorios" class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th style="text-align:center">Dice number</th>
                            <th>Territory</th>
                            <th style="text-align:center">Uses</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td style="text-align:center">- 1 -</td>
                            <td>Forest</td>
                            <td style="text-align:center"><b><c:out value="${usos[0]}"/></b></td>
                        </tr>
                        <tr>
                            <td style="text-align:center">- 2 -</td>
                            <td>Castle</td>
                            <td style="text-align:center"><b><c:out value="${usos[1]}"/></b></td>
                        </tr>
                        <tr>
                            <td style="text-align:center">- 3 -</td>
                            <td>Mountain</td>
                            <td style="text-align:center"><b><c:out value="${usos[2]}"/></b></td>
                        </tr>
                        <tr>
                            <td style="text-align:center">- 4 -</td>
                            <td>Village</td>
                            <td style="text-align:center"><b><c:out value="${usos[3]}"/></b></td>
                        </tr>
                        <tr>
                            <td style="text-align:center">- 5 -</td>
                            <td>Meadow</td>
                            <td style="text-align:center"><b><c:out value="${usos[4]}"/></b></td>
                        </tr>
                        <tr>
                            <td style="text-align:center">- 6 -</td>
                            <td>River</td>
                            <td style="text-align:center"><b><c:out value="${usos[5]}"/></b></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>    
    </body>
    
</petclinic:layout>
