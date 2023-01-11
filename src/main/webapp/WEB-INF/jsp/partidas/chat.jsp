<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="chat">
    <head> 
        <meta http-equiv="refresh" content="2"> 
    </head>
            <c:forEach items="${chat.mensajes}" var="m">
                        <c:choose>
                            <c:when test = "${m.user.username == user.username}">
                                <div style="text-align: right;">
                                    <c:out value="${m.user.username}"/>:<c:out value="${m.contenido}"/> 
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div style="text-align: left;">
                                    <c:out value="${m.user.username}"/>:<c:out value="${m.contenido}"/> 
                                </div>
                            </c:otherwise>
                        </c:choose> 
            </c:forEach>
            <button  class="btn btn-default">
                <a style="color:white" href="/chat/escribirMensaje/${id}">Envia un mensaje</a>
            </button>
</petclinic:layout>    