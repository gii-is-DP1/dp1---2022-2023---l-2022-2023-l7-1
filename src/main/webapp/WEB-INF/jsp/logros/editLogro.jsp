<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Edit logro">
    
    <h1><em><b><u>Edit Achievement</u></b></em></h1>

    <form:form modelAttribute="logro" class="form-horizontal" id="add-user-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Title" name="titulo"/>
            <petclinic:inputField label="Description" name="descripcion"/>
            <petclinic:inputField label="Logo" name="logo"/>
            <petclinic:inputField label="Requieremnt Points" name="reqPuntos"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Confirm</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>