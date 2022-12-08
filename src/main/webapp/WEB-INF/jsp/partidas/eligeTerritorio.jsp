<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<html  >
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
        <div class="col-sm-offset-2 col-sm-10">
            <input type=button class="btn btn-default" onclick="mostrarForm()" value="MODO MULTIJUGADOR"/>
	                <div id="Invitation" style="display: none;" class="modal-content">
                <form name="Invitation" >
                        <div class="form-group">
                            <form:input class="form-control" path="username" size="30" maxlength="80" placeholder="Friend's username"/>
                        </div>
	            <button type="submit" class="btn btn-default">Add player</button>
		        </form>
        </div>
    </body>
</html>
