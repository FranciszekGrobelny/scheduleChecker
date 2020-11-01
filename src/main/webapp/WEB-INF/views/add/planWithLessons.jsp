<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj lekcje do planu</title>
<%--    <link rel="stylesheet" type="text/css" href="<c:url value= "/resources/CSS/planAndLessonStyle.css"/>">--%>
    <style>
        table, th, td {
            border:  solid black 3px;
        }
    </style>
</head>
<body>
<a href="/addLesson?klasa=IV">klasa IV</a>
<table>
    <tr>
        <th>Nr</th>
        <th>Poniedziałek</th>
        <th>Wtorek</th>
        <th>Środa</th>
        <th>Czwartek</th>
        <th>Piątek</th>
    </tr>
<c:forEach var="j" begin="1" end="10">
    <tr>
        <td>${j}</td>
    <c:forEach var="i" begin="1" end="5">
        <td>
            <form method="post" action="/addLesson">
                Rewalidacja?<input type="checkbox" name="revalidationLesson" ><br/>
                Zajęcia Łączone?<input type="checkbox" name="connected" ><br/>
                <select name="topic" >
                    <c:forEach items="${topics}" var="topic">
                        <option value="${topic}">${topic}</option>
                    </c:forEach>
                </select><br>
                Numer sali <input type="number" name="room" ><br/>
                <select name="teacherId" >
                    <c:forEach items="${teachers}" var="teacher">
                        <option value="${teacher.id}">${teacher.initialLetters}</option>
                    </c:forEach>
                </select><br>
                <input type="hidden" name="dayId" value="${i}">
                <input type="hidden" name="lessonNumber" value="${j}">
                <input type="submit" placeholder="Dodaj lekcję">
            </form>
        </td>
    </c:forEach>
    </tr>
</c:forEach>
</table>
<button id="addLessons" type="button">Dodaj stwó©z kolejne lekcje</button>

<%--<script src="<c:url value= "/resources/js/lesson.js"/>"></script>--%>
</body>
</html>
