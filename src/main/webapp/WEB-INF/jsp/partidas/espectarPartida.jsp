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

    <head> 
        <meta http-equiv="refresh" content="3"> <!-- Recarga la pagina cada 3 segundo -->
    </head>

    <body>
        <h2><c:out value="${now}"/></h2>
        
        <petclinic:mapa> 
            <c:forEach items= "${acciones}" var= "accion">
                <petclinic:territorio accion="${accion}"/>
            </c:forEach>    
        </petclinic:mapa>


        <div class = "row">
            <div class="col-sm-3">
                <table id="territorios" class="table table-condensed table-bordered">
                    <thead>
                        <tr style="background-color:gainsboro">
                            <th>Territorio</th>
                            <th style="text-align:center">Usos</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr style="background-color:gainsboro">
                            <td>Bosque</td>
                            <td style="text-align:center"><b><c:out value="${usos[0]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td>Castillo</td>
                            <td style="text-align:center"><b><c:out value="${usos[1]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td>Montaña</td>
                            <td style="text-align:center"><b><c:out value="${usos[2]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td>Poblado</td>
                            <td style="text-align:center"><b><c:out value="${usos[3]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
                            <td>Pradera</td>
                            <td style="text-align:center"><b><c:out value="${usos[4]}"/></b></td>
                        </tr>
                        <tr style="background-color:gainsboro">
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
    
</petclinic:layout>
