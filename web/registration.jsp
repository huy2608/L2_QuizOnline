<%-- 
    Document   : registration
    Created on : Jan 24, 2021, 1:58:32 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    </head>
    <body>
        <h1>Create new account</h1>
        <form action="registNewAccount" method="POST">
            <c:set var="error" value="${requestScope.ERROR}"/>
            Email <input type="text" minlength="1" maxlength="50" name="txtEmail" 
                         value="${param.txtEmail}" required="required"/><br/>
            <c:if test="${not empty error.emailFormatError}">
                <font color ="red">${error.emailFormatError}</font></br>
            </c:if>
            Password <input type="password"  minlength="1" maxlength="50" name="txtPassword"
                            value="${param.txtPassword}"required="required" /><br/>

            Confirm <input type="password" minlength="1" maxlength="50" name="confirm"
                           value="${param.confirm}" required="required"/><br/>

            <c:if test="${not empty error.passwordConfirmError}">
                <font color ="red">${error.passwordConfirmError}</font></br>
            </c:if>

            Full Name <input type="text"minlength="1" maxlength="50" name="fullName"
                             value="${param.fullName}" required="required"/><br/>

            <input type="submit" value="Confirm"/>
        </form><br/>
        <a href="loginPage">Return to login</a>
    </body>
</html>
