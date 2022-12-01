<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<script>
    function mostrarForm(){ 
    var formulario = document.getElementById('Invitation');
    formulario.style.display = "block";  
}
</script>


<petclinic:layout pageName="CREAR PARTIDA">
    
    <form method = "post">
        <button class="btn btn-default" href="/partida1" type = "submit">Upvote</button>
    <form>
   
   

</petclinic:layout>
