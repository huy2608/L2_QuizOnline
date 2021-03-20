<%-- 
    Document   : createQuiz
    Created on : Mar 12, 2021, 12:45:21 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Quiz</title>
    </head>
    <body>
        <c:set var="name" value="${sessionScope.FULLNAME}"/>
        <c:set var="admin" value="${sessionScope.ADMIN}"/>
        <c:if test="${not empty name}">
            Welcome ${name}</br>
            <c:if test="${empty admin}">
                <form action="viewHistory" method="POST">
                    <input type="submit" value="History" />
                </form>
            </c:if>
            <form action="logout" method="POST">
                <input type="submit" value="Exit" />
            </form>     
            <h1>Create Quiz</h1>
            <form action="createQuiz" method="POST">
                <c:set var="error" value="${requestScope.QUIZERROR}"/>
                Start Date: <input type="date" name="startdate" value="${param.startdate}" /></br>
                <c:if test="${not empty error.invalidStartDate}">
                    <font color="red" >${error.invalidStartDate}</font></br>
                </c:if>
                End date: <input type="date" name="enddate" value="${param.enddate}" /></br>
                <c:if test="${not empty error.invalidEndDate}">
                    <font color="red" >${error.invalidEndDate}</font></br>
                </c:if>
                Time Duration: <input type="text" name="timeDuration" value="${param.timeDuration}" /></br>
                <c:if test="${not empty error.invalidTimeDuration}">
                    <font color="red" >${error.invalidTimeDuration}</font></br>
                </c:if>
                Number of question: <input type="number" name="numberOfQuestion" min="0" value="${param.numberOfQuestion}" /></br>
                <c:if test="${not empty error.numberOfQuestionOutOfOrder}">
                    <font color="red" >${error.numberOfQuestionOutOfOrder}</font></br>
                </c:if>
                <input type="hidden" name="subjectId" value="${param.subjectId}" />
                <input type="submit" value="Create" />
            </form>
            <a href="viewSubjectPage">Click here to return Subject Page</a>
        </c:if>
<c:if test="${empty name}">
    <a href="/PRJ231_2/">Return to login</a>
</c:if>
    </body>
</html>
