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
    .row{
        margin-top: -20%;
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
            <div class="col-sm-6">
                <c:if test="${eligeTerritorio == true}">
                    <h2><b>Elegir terriorio y número de territorios a dibujar:</b></h2>
                </c:if>
                <table id="territorioDado" class="table table-condensed">
                    <tbody>
                        <form:form modelAttribute="turno" class="form-horizontal">
                            <div class="form-select">
                                <tr>
                                    <form:select path = "territorio" class="btn btn-default">
                                                <form:options items = "${territorios}" style="background-color:gainsboro;color:black;text-align:left;"/>
                                    </form:select>
                                    <c:if test = "${numJugador ==1}">               
                                        <form:select path = "numTerritoriosJ1" class="btn btn-default">
                                    <form:options items = "${dados}" style="background-color:gainsboro;color:black;text-align:left;"/>
                                </form:select>
                                <td style="text-align:center;padding: 50px 0;">
                                    <div class = "row">
                                        <button class="btn btn-default" type="submit">Confirmar</button> 
                                    </div>
                                </td>
                            </c:if>
                            <c:if test = "${numJugador ==2}">               
                                <form:select path = "numTerritoriosJ2" class="btn btn-default">
                                    <form:options items = "${dados}" style="background-color:gainsboro;color:black;text-align:left;"/>
                                </form:select>
                                <td style="text-align:center;padding: 50px 0;">
                                    <div class = "row">
                                        <button class="btn btn-default" type="submit">Confirmar</button> 
                                    </div>
                                </td>
                            </c:if>
                            <c:if test = "${numJugador ==3}">               
                                <form:select path = "numTerritoriosJ3" class="btn btn-default">
                                    <form:options items = "${dados}" style="background-color:gainsboro;color:black;text-align:left;"/>
                                </form:select>
                                <td style="text-align:center;padding: 50px 0;">
                                    <div class = "row">
                                        <button class="btn btn-default" type="submit">Confirmar</button> 
                                    </div>
                                </td>
                            </c:if>
                            <c:if test = "${numJugador ==4}">               
                                <form:select path = "numTerritoriosJ4" class="btn btn-default">
                                    <form:options items = "${dados}" style="background-color:gainsboro;color:black;text-align:left;"/>
                                </form:select>
                                <td style="text-align:center;padding: 50px 0;">
                                    <div class = "row">
                                        <button class="btn btn-default" type="submit">Confirmar</button> 
                                    </div>
                                </td>
                            </c:if>
                                </tr>  
                            </div>
                        </form:form>
                    </tbody>
                </table>  
            </div>
            <div class="col-sm-1"></div>
            <div class="col-sm-3">
                <table id="territorios" class="table table-condensed table-bordered">
                    <thead>
                        <tr style="background-color:gainsboro">
                            <th style="text-align:center">Nº dado</th>
                            <th>Territorio</th>
                            <th style="text-align:center">Usos</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">- 1 -</td>
                            <td>Bosque</td>
                            <td style="text-align:center"><b><c:out value="${usos[0]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">- 2 -</td>
                            <td>Castillo</td>
                            <td style="text-align:center"><b><c:out value="${usos[1]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">- 3 -</td>
                            <td>Montaña</td>
                            <td style="text-align:center"><b><c:out value="${usos[2]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">- 4 -</td>
                            <td>Poblado</td>
                            <td style="text-align:center"><b><c:out value="${usos[3]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">- 5 -</td>
                            <td>Pradera</td>
                            <td style="text-align:center"><b><c:out value="${usos[4]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td style="text-align:center">- 6 -</td>
                            <td>Río</td>
                            <td style="text-align:center"><b><c:out value="${usos[5]}"/></b></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-sm-2">
                <table id="criterios" class="table table-condensed table-bordered">
                   <thead>
                        <tr>
                            <th style="text-align:center">Criterios</th>
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
            
            <!-- Para poder2 -->
            <c:if test = "${tablero.poder2 > 0}">               
                <c:out value="${tablero.poder2}"/>
            </c:if>
        </div>
    </body>
    <button>
        <a class="btn btn-default" href="/chat/${chatId}" target="_blank">Chat</a></th>
    </button>
    
</petclinic:layout>
