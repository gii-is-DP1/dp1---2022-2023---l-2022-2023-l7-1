<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="Edit username">
    
    <h1><em><b><u>Editar perfil</u></b></em></h1>

    <form:form modelAttribute="user" class="form-horizontal" id="add-user-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="name"/>
            <petclinic:inputField label="Apellidos" name="lastName"/>
            <petclinic:inputField label="Correo" name="email"/>
            <petclinic:inputField label="Teléfono" name="phone"/>
            <petclinic:inputField label="Cumpleaños" name="birthDate"/>
            <petclinic:inputField label="Contraseña" name="password"/>  
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Confirmar</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>