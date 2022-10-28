<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="kingdomMaps" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %--> 

<style>
    body {
        background-image: url("/resources/images/mapasDelReino.png");
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: 100% 100%;
    }
</style>

<kingdomMaps:layout pageName="home">
    <body>          
        <div class="row">
        </div>
        <div class="row">
            <div class="col-md-12">
                <!-- <spring:url value="/resources/images/descarga.png" htmlEscape="true" var="petsImage"/>
                <img class="img-responsive" src="${petsImage}"/> -->
            </div>
        </div>
    </body>
</kingdomMaps:layout>
