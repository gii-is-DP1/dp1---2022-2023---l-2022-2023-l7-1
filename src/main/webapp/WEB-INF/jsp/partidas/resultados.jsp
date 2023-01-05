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
    table{
        margin-top: -20%;
    }
   
</style>

<petclinic:layout pageName="RESULTS">
    <body>
        <h2><c:out value="${now}"/></h2>
        
        <petclinic:mapa> 
            <c:forEach items= "${acciones}" var= "accion">
                <petclinic:territorio accion="${accion}"/>
            </c:forEach>    
        </petclinic:mapa>

        

        
        <div class="d-flex align-items-center">
            <table id="criterios" class="table table-condensed table-bordered">
               <thead>
                    <tr>
                        <th style="text-align:center" colspan="2">RESULTADOS DEL JUEGO</th>
                    </tr>
                    
               </thead>
               <tbody>
                    <tr>
                        <td style="text-align:center">Criterio A<c:out value="${criterios[0]}"/></td>
                        <td style="text-align:center"><c:out value="${criterioA1}"/> puntos</td>
                    </tr>
                    <tr>
                        <td style="text-align:center">Criterio A<c:out value="${criterios[1]}"/></td>
                        <td style="text-align:center"><c:out value="${criterioA2}"/> puntos</td>
                    </tr>
                    <tr>
                        <td style="text-align:center">Criterio B<c:out value="${criterios[2]}"/></td>
                        <td style="text-align:center"><c:out value="${criterioB1}"/> puntos</td>
                    </tr>
                    <tr>
                        <td style="text-align:center">Criterio B<c:out value="${criterios[3]}"/></td>
                        <td style="text-align:center"><c:out value="${criterioB2}"/> puntos</td>
                    </tr>
                    <tr>
                        <td style="text-align:center">Poder ?</td>
                        <td style="text-align:center"><c:out value="${poder2}"/> puntos</td>
                    </tr>
                    <tr>
                        <td style="text-align:center"><b>Puntos totales</b></td>
                        <td style="text-align:center"><b><c:out value="${puntosTotales}"/> puntos</b></td>
                    </tr>                   
                        
               </tbody>
               
            </table>
            <b><a style="position: absolute; margin-left: 1075px; " href="/" class="btn btn-default">Ir a inicio</a></b>
            
        </div>  
       
    
    </body>
    
        
    
</petclinic:layout>