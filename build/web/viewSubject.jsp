<%-- 
    Document   : viewSubject
    Created on : Jan 24, 2021, 2:39:41 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subject Page</title>
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

            <h1>Subject!</h1>
            <c:if test="${empty name}">
                <form action="returnLogin">
                    <input type="submit" value="Return to login" />
                </form>
            </c:if>
            <c:if test="${not empty name}">
                <c:if test="${not empty admin}">
                    <c:set var="list" value="${sessionScope.SUBJECTLIST}"/>
                    <c:if test="${not empty list}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Subject</th>
                                    <th>View Question</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="listsubject" items="${list}" varStatus="counter">
                                <form action="viewQuestion" method="POST">
                                    <tr>
                                        <td>${counter.count}.</td>
                                        <td>${listsubject.name}</td>
                                        <td>
                                            <input type="hidden" name="subjectId" value="${listsubject.subjectId}" />
                                            <input type="hidden" name="subjectName" value="${listsubject.name}" />
                                            <input type="submit" value="View Question" />
                                        </td>
                                        <td>
                                            <c:url var="createQuiz" value="createQuizPage">
                                                <c:param name="subjectId" value="${listsubject.subjectId}"/>
                                                <c:param name="subjectName" value="${listsubject.name}"/>
                                            </c:url>
                                            <a href="${createQuiz}"> Create new quiz</a>
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </c:if>
        </c:if>

        <c:if test="${not empty name}">
            <c:if test="${  empty admin}">
                <c:set var="list" value="${sessionScope.SUBJECTLIST}"/>
                <c:if test="${not empty list}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Subject</th>
                                <th>Take Quiz</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="listsubject" items="${list}" varStatus="counter">
                            <form action="takeQuiz" method="POST">
                                <tr>
                                    <td>${counter.count}.</td>
                                    <td>${listsubject.name}</td>
                                    <td>
                                        <input type="hidden" name="txtsubjectId" value="${listsubject.subjectId}" />
                                        <input type="hidden" name="txtSubjectName" value="${listsubject.name}" />
                                        <input type="submit" value="Take Quiz" />
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:set var="err" value="${requestScope.ERROR}"/>
            <c:if test="${not empty err}">
                <font color="red" >${err}</font>
            </c:if>
        </c:if>
    </c:if>
    <c:if test="${not empty requestScope.QUIZERR}">
        ${requestScope.QUIZERR}
    </c:if>
</c:if>
<c:if test="${empty name}">
    <a href="/PRJ231_2/">Return to login</a>
</c:if>
</body>
</html>
