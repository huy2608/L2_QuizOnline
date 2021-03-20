<%-- 
    Document   : create
    Created on : Jan 29, 2021, 8:48:30 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
        <c:set var="name" value="${sessionScope.FULLNAME}"/>
        <c:if test="${not empty name}">
            Welcome ${name}</br>
            <form action="logout" method="POST">
                <input type="submit" value="Exit" />
            </form>        
            <h1>Create New Question!</h1>
            <form action="create" method="POST">
                <c:set var="subjectName" value="${param.subjectName}"/>
                <c:set var="questionErr" value="${requestScope.QUESTIONERR}"/>
                <c:set var="answerErr" value="${requestScope.ANSWERERR}"/>
                Question Content: <input type="text" name="txtQuestionContent" value="${param.txtQuestionContent}" /></br>
                <c:if test="${not empty questionErr.questionContentLengthErr}">
                    <font color="red">${questionErr.questionContentLengthErr}</font></br>
                </c:if>

                <input type="radio" name="Answer" value="A" checked="checked"/> a. <input type="text" name="txtAnswer1" value="${param.txtAnswer1}"/></br>
                <c:if test="${not empty answerErr.answerALengthErr}">
                    <font color="red">${answerErr.answerALengthErr}</font></br>
                </c:if>
                <input type="radio" name="Answer" value="B"/> b. <input type="text" name="txtAnswer2" value="${param.txtAnswer2}" /></br>
                <c:if test="${not empty answerErr.answerBLengthErr}">
                    <font color="red">${answerErr.answerBLengthErr}</font></br>
                </c:if>
                <input type="radio" name="Answer" value="C"/> c. <input type="text" name="txtAnswer3" value="${param.txtAnswer3}" /></br>
                <c:if test="${not empty answerErr.answerCLengthErr}">
                    <font color="red">${answerErr.answerCLengthErr}</font></br>
                </c:if>
                <input type="radio" name="Answer" value="D"/> d. <input type="text" name="txtAnswer4" value="${param.txtAnswer4}" /></br>
                <c:if test="${not empty answerErr.answerDLengthErr}">
                    <font color="red">${answerErr.answerDLengthErr}</font></br>
                </c:if>
                <input type="submit" value="Create" />
                <input type="hidden" name="subjectName" value="${param.subjectName}" />
                <c:url var="viewQuestion" value="viewQuestion">
                    <c:param name="subjectName" value="${subjectName}"/>
                    <c:param name="subjectId" value="${sessionScope.SUBJECTID}"/>
                </c:url>
                <c:if test="${not empty answerErr.answerDuplicated}">
                    <font color="red">${answerErr.answerDuplicated}</font></br>
                </c:if>
                <a href="${viewQuestion}">Return to Question Page</a>
            </form>
        </c:if>
        <c:if test="${empty name}">
            <a href="/PRJ231_2/">Return to login</a>
        </c:if>
    </body>
</html>
