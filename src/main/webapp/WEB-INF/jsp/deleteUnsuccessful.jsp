<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/dragonDP.png" var="dragonDP"/>
    <img src="${dragonDP}"/>

    <h2>Parece ser que intenta borrar a un usuario con partidas existentes</h2>

    <h2>Si desea borrar a este usuario, borre todas sus partidas manualmente con antelacion</h2>

</petclinic:layout>