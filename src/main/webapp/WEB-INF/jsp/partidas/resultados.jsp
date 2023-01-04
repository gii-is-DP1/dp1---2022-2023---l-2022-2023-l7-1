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
                        <th style="text-align:center" colspan="2">Game Results</th>
                    </tr>
                    
               </thead>
               <tbody>
                    <tr>
                        <td style="text-align:center">Criterion A<c:out value="${criterios[0]}"/></td>
                        <td style="text-align:center"><c:out value="${criterioA1}"/> points</td>
                    </tr>
                    <tr>
                        <td style="text-align:center">Criterion A<c:out value="${criterios[1]}"/></td>
                        <td style="text-align:center"><c:out value="${criterioA2}"/> points</td>
                    </tr>
                    <tr>
                        <td style="text-align:center">Criterion B<c:out value="${criterios[2]}"/></td>
                        <td style="text-align:center"><c:out value="${criterioB1}"/> points</td>
                    </tr>
                    <tr>
                        <td style="text-align:center">Criterion B<c:out value="${criterios[3]}"/></td>
                        <td style="text-align:center"><c:out value="${criterioB2}"/> points</td>
                    </tr>
                    <tr>
                        <td style="text-align:center">Power ?</td>
                        <td style="text-align:center"><c:out value="${poder2}"/> points</td>
                    </tr>
                    <tr>
                        <td style="text-align:center"><b>Total Points</b></td>
                        <td style="text-align:center"><b><c:out value="${puntosTotales}"/> points</b></td>
                    </tr>                   
                        
               </tbody>
               
            </table>
            <b><a style="position: absolute; margin-left: 1075px; " href="/" class="btn btn-default">Go Home</a></b>
            
        </div>  
       
    
    </body>
    
        
    
</petclinic:layout>