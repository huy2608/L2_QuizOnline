<%-- 
    Document   : viewQuestion
    Created on : Jan 25, 2021, 8:14:02 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Question Page</title>
    </head>
    <body>
        <c:set var="name" value="${sessionScope.FULLNAME}"/>
        <c:if test="${not empty name}">
            Welcome ${name}</br>
            <form action="logout" method="POST">
                <input type="submit" value="Exit" />
            </form>        
            <c:set var="subjectName" value="${requestScope.SUBJECTNAME}"/>
            <c:set var="status" value="${requestScope.STATUS}"/>
            <c:set var="error" value="${requestScope.ERROR}"/>
            <c:if test="${not empty subjectName}">
                <h1>${subjectName}</h1>
                <c:url var="create" value="createPage">
                    <c:param name="subjectName" value="${subjectName}"/>
                </c:url>
                <a href="${create}">Create New Question</a>
                <form action="search">
                    Search: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
                    Status: <select name="cbStatusOfSearch">
                        <option value="">--Choose--</option>
                        <option value="Activate"<c:if test="${param.cbStatusOfSearch eq 'Activate'}">selected</c:if>>Activate</option>
                        <option value="DeActivate"<c:if test="${param.cbStatusOfSearch eq 'DeActivate'}">selected</c:if>>DeActivate</option>
                        </select>
                        <input type="hidden" name="subjectName" value="${subjectName}" />
                    <input type="submit" value="Search" />
                </form>
                <c:set var="currentPage" value="${sessionScope.CURRENTPAGE}"/> 
                <c:set var="noOfPage" value="${sessionScope.NOOFPAGE}"/>
                <c:set var="noOfRecord" value="${sessionScope.NOOFPAGE}"/>
                <c:set var="questionlist" value="${sessionScope.QUESTIONLIST}"/>
                <c:if test="${not empty questionlist}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Question</th>
                                <th>Answer</th>
                                <th>Status</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="list" items="${questionlist}" varStatus="counter">
                            <form action="update" method="POST">
                                <tr>
                                    <td>${counter.count}.</td>
                                    <td>
                                        ${counter.count}.${list.question_content}</br>
                                        <c:set var="questionid" value="${list.questionId}"/>
                                        <c:set var="answer" value="${sessionScope.ANSWERLIST}"/>
                                        <c:if test="${not empty answer}">
                                            <c:forEach var="answerlist" items="${answer[questionid]}">       
                                                ${answerlist.answerId}.${answerlist.answer_content}</br>
                                            </c:forEach>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:set var="correctAnswer" value="${sessionScope.ANSWERCORRECT}"/>
                                        <c:if test="${not empty correctAnswer}">
                                            <c:forEach var="correctanswer" items="${correctAnswer[questionid]}">       
                                                ${correctanswer}</br>
                                            </c:forEach>
                                        </c:if>
                                    </td>
                                    <td>
                                        ${list.status}
                                    </td>
                                    <td>       
                                        <input type="submit" value="Update" />
                                        <input type="hidden" name="txtQuestionId" value="${questionid}" />
                                        <input type="hidden" name="txtSearchValue" value="${requestScope.SEARCHVALUE}" />
                                        <input type="hidden" name="txtSubjectId" value="${list.subjectId}" />
                                        <input type="hidden" name="txtQuestionContent" value="${list.question_content}" />
                                        <input type="hidden" name="cbStatus" value="${list.status}" />
                                        <input type="hidden" name="cbStatusOfSearch" value="${requestScope.STATUSOFSEARCH}" />
                                        <input type="hidden" name="subjectName" value="${subjectName}" />
                                        <input type="hidden" name="page" value="${currentPage}" />
                                    </td>
                                    <td>
                                        <c:url var="delete" value="delete">
                                            <c:param name="txtQuestionId" value="${list.questionId}"/>
                                            <c:param name="txtSubjectId" value="${list.subjectId}"/>
                                            <c:param name="txtSearchValue" value="${requestScope.SEARCHVALUE}" />
                                            <c:param name="subjectName" value="${subjectName}"/>
                                            <c:param name="cbStatus" value="${requestScope.STATUSOFSEARCH}"/>
                                            <c:param name="page" value="${currentPage}"/>
                                        </c:url>
                                        <a href="${delete}">Delete</a>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${currentPage != 1}">
                <td>
                    <c:url var="viewQuestion" value="search">
                        <c:param name="subjectId" value="${sessionScope.SUBJECTID}"/>
                        <c:param name="subjectName" value="${subjectName}"/>
                        <c:param name="txtSearchValue" value="${requestScope.SEARCHVALUE}" />
                        <c:param name="cbStatus" value="${status}"/>
                        <c:param name="page" value="${currentPage - 1}"/>
                    </c:url>
                    <a href="${viewQuestion}">Previous</a>
                </td>
            </c:if>

            <%--For displaying Page numbers. 
            The when condition does not display a link for the current page--%>
            <table border="1" cellpadding="5" cellspacing="5">
                <tr>
                    <c:forEach begin="1" end="${sessionScope.NOOFPAGE}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <c:url var="viewQuestion" value="search">
                                        <c:param name="subjectId" value="${sessionScope.SUBJECTID}"/>
                                        <c:param name="subjectName" value="${subjectName}"/>
                                        <c:param name="txtSearchValue" value="${requestScope.SEARCHVALUE}" />
                                        <c:param name="cbStatus" value="${status}"/>
                                        <c:param name="page" value="${i}"/>
                                    </c:url>
                                    <a href="${viewQuestion}">${i}</a>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>

            <%--For displaying Next link --%>
            <c:if test="${currentPage lt noOfPage}">
                <td>
                    <c:url var="viewQuestion" value="search">
                        <c:param name="subjectId" value="${sessionScope.SUBJECTID}"/>
                        <c:param name="subjectName" value="${subjectName}"/>
                        <c:param name="txtSearchValue" value="${requestScope.SEARCHVALUE}" />
                        <c:param name="cbStatus" value="${status}"/>
                        <c:param name="page" value="${currentPage + 1}"/>
                    </c:url>
                    <a href="${viewQuestion}">Next</a>
                </td>
            </c:if>
        </c:if>
        <c:if test="${empty questionlist}">
            Empty Question </br>
        </c:if>
        <c:if test="${not empty error}">
            ${error} </br>
        </c:if>
    </c:if>
    <a href="viewSubjectPage">Click here to return Subject Page</a>
</c:if>
<c:if test="${empty name}">
    <a href="/PRJ231_2/">Return to login</a>
</c:if>
</body>
</html>
