<%-- 
    Document   : viewHistoryDetail
    Created on : Mar 17, 2021, 3:06:27 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Detail Page</title>
    </head>
    <body>
        <c:set var="name" value="${sessionScope.FULLNAME}"/>
        <c:if test="${not empty name}">
            Welcome ${name}</br>
            <form action="logout" method="POST">
                <input type="submit" value="Exit" />
            </form>        
            <h1>History Detail!</h1>
            <c:set var="questionlist" value="${sessionScope.QUESTIONLISTOFQUIZ}"/>
            <c:set var="correct" value="${sessionScope.ANSWERCORRECT}"/>
            <c:set var="useranswer" value="${sessionScope.ANSWEROFUSER}"/>
            <div id="myDiv">  
                <c:if test="${not empty questionlist}">
                    <form name="finish" id="finish" action="finish" method="POST">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Question</th>
                                    <th>Correct Answer</th>
                                    <th>Your Answer</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="list" items="${questionlist}" varStatus="counter">
                                    <c:set var="questionContent" value="${list.key}"/>
                                    <tr>
                                        <td>${counter.count}.</td>
                                        <td>
                                            ${list.key}</br>
                                            <c:set var="count" value="${counter.count}"/>
                                            <c:forEach var="answerlist" items="${list.value}" varStatus="counter">       
                                                &#${counter.index + 65};. ${answerlist.answerContent}</br>
                                            </c:forEach>
                                            <input type="hidden" name="txtQuestionId" value="${questionid}" />  
                                        </td>
                                        <td>
                                            ${correct[questionContent]}
                                        </td>
                                        <td>${useranswer[questionContent]}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </c:if>
                <a href="viewHistory">Return</a>
            </c:if>
            <c:if test="${empty name}">
                <a href="/PRJ231_2/">Return to login</a>
            </c:if>
    </body>
</html>
