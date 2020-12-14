<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj ustawienia</title>
</head>
<body>
<a href="/addTeacher">Dodaj nauczycieli</a>
<form method="POST" >
    <c:choose>
        <c:when test="${settings.size() > 0}">
            <label for="longBreakAfterLesson">Po której lekcji jest długa przerwa: </label><input type="number" name="longBreakAfterLesson" id="longBreakAfterLesson" value="${settings.get(0)}"/><br/>
            <label for="firstLessonStartTime">O której godzinie zaczyna się pierwsza lekcja:</label> <input type="time" name="firstLessonStartTime" id="firstLessonStartTime" value="${settings.get(1)}"/><br/>
            <label for="shortBreak">Ile trwa krótka przerwa:</label> <input type="time" name="shortBreak" id="shortBreak" value="${settings.get(2)}"/><br/>
            <label for="longBreak">Ile trwa długa przerwa:</label> <input type="time" name="longBreak" id="longBreak" value="${settings.get(3)}"/><br/>
        </c:when>
        <c:otherwise>
            <label for="longBreakAfterLesson">Po której lekcji jest długa przerwa: </label><input type="text" name="longBreakAfterLesson" id="longBreakAfterLesson"/><br/>
            <label for="firstLessonStartTime">O której godzinie zaczyna się pierwsza lekcja:</label> <input type="time" name="firstLessonStartTime" id="firstLessonStartTime"/><br/>
            <label for="shortBreak">Ile trwa krótka przerwa:</label> <input type="time" name="shortBreak" id="shortBreak"/><br/>
            <label for="longBreak">Ile trwa długa przerwa:</label> <input type="time" name="longBreak" id="longBreak"/><br/>
        </c:otherwise>
    </c:choose>

    <input type="Submit" value="zapisz ustawienia"/>
    <sec:csrfInput/>
</form>
</body>
</html>
