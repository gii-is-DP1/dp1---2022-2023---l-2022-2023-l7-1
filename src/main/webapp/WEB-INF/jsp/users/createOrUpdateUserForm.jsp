<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="register">
    <h1><em><b><u>User Data</u></b></em></h1>

    <form:form modelAttribute="user" class="form-horizontal" id="add-user-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="First Name" name="name"/>
            <petclinic:inputField label="Last Name" name="lastName"/>
            <petclinic:inputField label="Birthdate" name="birthDate"/>
            <petclinic:inputField label="Email" name="email"/>
            <petclinic:inputField label="Telephone" name="phone"/>
            <hr>
            <petclinic:inputField label="Username" name="username"/>
            <petclinic:passwordField label="Password" name="password"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Confirm</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>
