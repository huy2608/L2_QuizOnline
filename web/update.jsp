<%-- 
    Document   : update
    Created on : Jan 27, 2021, 7:18:41 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <c:set var="name" value="${sessionScope.FULLNAME}"/>
        <c:if test="${not empty name}">
            Welcome ${name}</br>
            <form action="logout" method="POST">
                <input type="submit" value="Exit" />
            </form>        
            <h1>Update!</h1>
            <form action="confirm" method="POST">
                <c:set var="questionerror" value="${requestScope.QUESTIONERROR}"/>
                <c:set var="answererror" value="${requestScope.ANSWERERROR}"/>
                Question Content: <input type="text" name="txtQuestionContent" value="${requestScope.QUESTIONCONTENT}" /></br>
                <c:if test="${not empty questionerror.questionContentLengthErr}">
                    <font color="red">${questionerror.questionContentLengthErr}</font></br>
                </c:if>
                <c:set var="answer" value="${sessionScope.ANSWERLIST}"/>
                <c:set var="questionid" value="${requestScope.QUESTIONID}"/>
                <c:if test="${not empty answer}">
                    <c:forEach var="answerlist" items="${answer[questionid]}" varStatus="counter">       
                        <input type="radio" name="Answer" value="${answerlist.answerId}"
                               <c:if test="${answerlist.isCorrect}">
                                   checked="checked"
                               </c:if> 
                               />
                        ${answerlist.answerId}.<input type="text" name="${counter.count}" value="${answerlist.answer_content}" /></br>
                    </c:forEach>
                    <c:if test="${not empty answererror}">
                        <font color="red">Update false, Please try again</font></br>
                    </c:if>
                </c:if>
                Status: <select name="cbStatus"></br>
                    <option <c:if test="${requestScope.STATUS eq 'Activate'}">selected</c:if>>Activate</option>
                    <option <c:if test="${requestScope.STATUS eq 'DeActivate'}">selected</c:if>>DeActivate</option>
                    </select>
                    <input type="submit" value="Confirm" /></br>
                    <input type="hidden" name="txtQuestionId" value="${requestScope.QUESTIONID}" />
                <input type="hidden" name="txtSubjectId" value="${sessionScope.SUBJECTID}" />
                <input type="hidden" name="txtQuestionContent" value="${txtQuestionContent}" />
                <input type="hidden" name="cbStatus" value="${cbStatus}" />
                <input type="hidden" name="cbStatusOfSearch" value="${requestScope.STATUSOFSEARCH}" />
                <input type="hidden" name="subjectName" value="${requestScope.SUBJECTNAME}" />
                <input type="hidden" name="txtSearchValue" value="${requestScope.SEARCHVALUE}" />
                <input type="hidden" name="page" value="${sessionScope.CURRENTPAGE}" />
                <c:url var="viewQuestion" value="search">
                    <c:param name="subjectName" value="${requestScope.SUBJECTNAME}"/>
                    <c:param name="subjectId" value="${sessionScope.SUBJECTID}"/>
                    <c:param name="txtSearchValue" value="${requestScope.SEARCHVALUE}"/>
                    <c:param name="cbStatus" value="${requestScope.SEARCHVALUE}"/>
                    <c:param name="page" value="${1}"/>
                </c:url>
                <a href="${viewQuestion}">Return to Question Page</a>
            </form>
        </c:if>
        <c:if test="${empty name}">
            <a href="/PRJ231_2/">Return to login</a>
        </c:if>
    </body>
</html>
