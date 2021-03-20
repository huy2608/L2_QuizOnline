<%-- 
    Document   : viewHistory
    Created on : Feb 2, 2021, 2:53:10 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
    </head>
    <body>
        <c:set var="name" value="${sessionScope.FULLNAME}"/>
        <c:if test="${not empty name}">
            Welcome ${name}</br>
            <form action="logout" method="POST">
                <input type="submit" value="Exit" />
            </form>        

            <h1>History!</h1>
            <c:set var="currentPage" value="${sessionScope.CURRENTPAGEHISTORY}"/> 
            <c:set var="noOfPage" value="${sessionScope.NOOFPAGEHISTORY}"/>
            <c:set var="noOfRecord" value="${sessionScope.NOOFRECORDHISTORY}"/>
            <c:set var="quizList" value="${sessionScope.QUIZHISTORY}"/>
            <form action="searchHistory" method="POST">
                Search: <input type="text" name="txtSearchSubjectName" value="${param.txtSearchSubjectName}" />
                <input type="submit" value="Search" />
            </form>
            <c:if test="${not empty quizList}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Subject Name</th>
                            <th>Score</th>
                            <th>Time Duration</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="list" items="${quizList}" varStatus="counter">
                            <tr>
                                <td>${counter.count}.</td>
                                <td>
                                    ${list.subjectName}
                                </td>
                                <td>
                                    ${list.totalPoint}
                                </td>
                                <td>
                                    ${list.timeDuration}
                                </td>
                                <td>
                                    <c:url var="viewDetail" value="viewHistoryDetail">
                                        <c:param name="userQuizId" value="${list.userQuizId}"/>
                                    </c:url>
                                    <a href="${viewDetail}">View Detail</a>
                                </td>
                            </tr>
                        </c:forEach> 
                    </tbody>
                </table>
                <table border="1" cellpadding="5" cellspacing="5">
                    <tr>
                        <c:forEach begin="1" end="${noOfPage}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <td>${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <c:url var="viewQuestion" value="viewHistory">
                                            <c:param name="page" value="${i}"/>
                                        </c:url>
                                        <a href="${viewQuestion}">${i}</a>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </table>
            </c:if>
            <c:if test="${empty quizList}">
                No Result
            </c:if>
            <a href="viewSubjectPage">Click here to return</a>
        </c:if>
        <c:if test="${empty name}">
            <a href="/PRJ231_2/">Return to login</a>
        </c:if>

    </body>
</html>
