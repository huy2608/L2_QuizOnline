<%-- 
    Document   : takeQuiz
    Created on : Jan 30, 2021, 12:06:44 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Page</title>
    </head>
    <body>
        <c:set var="name" value="${sessionScope.FULLNAME}"/>
        <c:if test="${not empty name}">
            Welcome ${name}</br>
            <form action="logout" method="POST">
                <input type="submit" value="Exit" />
            </form>        
            <c:set var="subjectName" value="${requestScope.SUBJECTNAME}"/>
            <c:if test="${not empty subjectName}">
                <h1>${subjectName}</h1>
                <style>
                    div#myDiv{
                        overflow-y:scroll;  
                        overflow-x: hidden;
                        height:300px;
                        width: 700px;
                        border: 1px solid #000;
                        border-radius: 7px;
                    }
                </style>
                <style>
                    p {
                        text-align: center;
                        font-size: 60px;
                        margin-top: 0px;
                    }
                </style>
                <p id="demo"></p>

                <script>
                    var timeDuration = ${sessionScope.TIMEDURATION};
                    var days = Math.floor(timeDuration / (1000 * 60 * 60 * 24));
                    var hours = Math.floor((timeDuration % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                    var minutes = Math.floor((timeDuration % (1000 * 60 * 60)) / (1000 * 60));
                    var seconds = Math.floor((timeDuration % (1000 * 60)) / 1000);
                    document.getElementById("demo").innerHTML = days + "d " + hours + "h "
                            + minutes + "m " + seconds + "s ";

                    var x = setInterval(function () {
                        // Find the distance between now and the count down date
                        var distance = timeDuration;

                        // Time calculations for days, hours, minutes and seconds
                        var days = Math.floor(distance / (1000 * 60 * 60 * 24));
                        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                        // Output the result in an element with id="demo"
                        document.getElementById("demo").innerHTML = days + "d " + hours + "h "
                                + minutes + "m " + seconds + "s ";
                        timeDuration = timeDuration - 1000;
                        // If the count down is over, write some text 
                        if (distance < 0) {
                            clearInterval(x);
                            document.forms["finish"].submit();
                        }
                    }, 1000);
//                    }
                </script>

                <c:set var="questionlist" value="${sessionScope.QUESTIONLISTOFQUIZ}"/>
                <div id="myDiv">  
                    <c:if test="${not empty questionlist}">
                        <form name="finish" id="finish" action="finish" method="POST">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Question</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="list" items="${questionlist}" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}.</td>
                                            <td>
                                                ${list.key}</br>
                                                <c:set var="count" value="${counter.count}"/>
                                                <c:set var="question" value="${list.key}"/>
                                                <c:forEach var="answerlist" items="${list.value}" varStatus="counter">       
                                                    <input type="radio" name="${question}" value="${answerlist.answerContent}" />&#${counter.index + 65};. ${answerlist.answerContent}</br>
                                                </c:forEach>
                                                <input type="hidden" name="txtQuestionId" value="${questionid}" />  
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <input type="hidden" name="subjectname" value="${subjectName}" />
                            <input type="hidden" name="startTime" value="${requestScope.STARTTIME}" />
                            <input type="submit" value="Finish" />
                        </form>
                    </c:if>
                </div> 

            </c:if>
        </c:if>
        <c:if test="${empty name}">
            <a href="/PRJ231_2/">Return to login</a>
        </c:if>
    </body>
</html>
