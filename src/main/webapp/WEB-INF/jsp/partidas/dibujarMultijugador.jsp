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
                <h2>Dibujar:</h2>
                <small>Casillas pendientes a dibujar: <b><c:out value="${porDibujar}"/></b></small>
                <table id="dibujar" class="table table-condensed">
                    <tbody>
                        <form:form modelAttribute="action">
                            
                                <div class="form-select">
                                    <tr>
                                        <td style="text-align:center;padding: 25px 0;">
                                            <h3>Elige casilla para dibujar:</h3>
                                        </td>
                                        <td style="text-align:center;padding: 25px 0;">
                                            <form:select path = "casilla" class="btn btn-default">    
                                                <form:options items = "${casillas}" style="background-color:gainsboro;color:black;text-align:left;"/>
                                            </form:select>
                                        </td>
                                        <c:if test = "${tablero.poder1 == 0}">
                                            <td style="text-align:center;padding: 47px 0;">
                                                <div class = "row">
                                                    <button class="btn btn-default" type="submit">Confirmar</button> 
                                                </div>
                                            </td>
                                        </c:if>
                                        <c:if test = "${tablero.poder1 > 0}">
                                            <td style="text-align:center;padding: 90px 0;" rowspan="2">
                                                <div class = "row">
                                                    <button class="btn btn-default" type="submit">Confirmar</button> 
                                                </div>
                                            </td>
                                        </c:if>
                                    </tr>
                                    <c:if test = "${tablero.poder1 > 0}">
                                    <tr>
                                        <td style="text-align:center;padding: 25px 0;">
                                            <h3>Poder +-1:</h3>
                                            <small>Usos: <b><c:out value="${poder1}"/></b></small>
                                        </td>
                                        <td style="text-align:center;padding: 25px 0;">
                                            <form:form modelAttribute="turno">
                                                <c:if test = "${numJugador ==1}">               
                                                <form:select path = "numTerritoriosJ1" class="btn btn-default">
                                                    <form:options items = "${poder}" style="background-color:gainsboro;color:black;text-align:left;"/>
                                                </form:select>
                                                </c:if>
                                                <c:if test = "${numJugador ==2}">               
                                                <form:select path = "numTerritoriosJ2" class="btn btn-default">
                                                    <form:options items = "${poder}" style="background-color:gainsboro;color:black;text-align:left;"/>
                                                </form:select>
                                                </c:if>
                                                <c:if test = "${numJugador ==3}">               
                                                <form:select path = "numTerritoriosJ3" class="btn btn-default">
                                                    <form:options items = "${poder}" style="background-color:gainsboro;color:black;text-align:left;"/>
                                                </form:select>
                                                </c:if>
                                                <c:if test = "${numJugador ==4}">               
                                                <form:select path = "numTerritoriosJ4" class="btn btn-default">
                                                    <form:options items = "${poder}" style="background-color:gainsboro;color:black;text-align:left;"/>
                                                </form:select>
                                                </c:if>
                                            </form:form>
                                        </td>
                                    </c:if>
                                    </td>
                                </tr>  
                            </div>
                        </form:form>
                    </tbody>
                </table>
            </div>
            <div class="col-sm-4"></div>

            <div class="col-sm-3">
                <h2>Poder ?:</h2>
                <small>Puntos conseguidos al usar ?: <b><c:out value="${tablero.poder2}"/></b></small>
            </div>

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
    <button>
        <a class="btn btn-default" href="/chat/${chatId}" target="_blank">Chat</a></th>
    </button>
    
</petclinic:layout>