<%-- 
    Document   : viewPoint
    Created on : Jan 30, 2021, 2:12:37 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Point Page</title>
    </head>
    <body>
        <c:set var="name" value="${sessionScope.FULLNAME}"/>
        <c:if test="${not empty name}">
            Welcome ${name}</br>
            <form action="logout" method="POST">
                <input type="submit" value="Exit" />
            </form>  
            <form action="viewHistory" method="POST">
                <input type="submit" value="History" />
            </form>
            <c:set var="subjectName" value="${requestScope.SUBJECTNAME}"/>
            <h1>${subjectName}</h1>
            <c:set var="score" value="${requestScope.POINT}"/>
            Your score: <font color="red">${score}</font>/10.0</br>



            <a href="viewSubjectPage">Take new Quiz</a>
        </c:if>
<c:if test="${empty name}">
    <a href="/PRJ231_2/">Return to login</a>
</c:if>
    </body>
</html>
