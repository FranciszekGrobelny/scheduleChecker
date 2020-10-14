<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj ustawienia</title>
</head>
<body>
<form method="POST" >
    <c:choose>
        <c:when test="${settings.size() > 0}">
            <label for="longBreakAfterLesson">Po której lekcji jest długa przerwa: </label><input type="number" name="longBreakAfterLesson" id="longBreakAfterLesson" value="${settings.get(0)}"/><br/>
            <label for="firstLessonStartTime">O której godzinie zaczyna się pierwsza lekcja:</label> <input type="time" name="firstLessonStartTime" id="firstLessonStartTime" value="${settings.get(1)}"/><br/>
            <label for="breakAfter45minLesson">Ile wynosi przerwa  dla lekcji 45 min:</label> <input type="time" name="breakAfter45minLesson" id="breakAfter45minLesson" value="${settings.get(2)}"/><br/>
            <label for="breakAfter60minLesson">Ile wynosi przerwa dla lekcji 60 min:</label> <input type="time" name="breakAfter60minLesson" id="breakAfter60minLesson" value="${settings.get(3)}"/><br/>
            <label for="longBreakFor45minLesson">Ile wynosi długa przerwa dal lekcji 45 min: </label><input type="time" name="longBreakFor45minLesson" id="longBreakFor45minLesson" value="${settings.get(4)}"/><br/>
            <label for="longBreakFor60minLesson">Ile wynosi długa przerwa dal lekcji 60 min: </label><input type="time" name="longBreakFor60minLesson" id="longBreakFor60minLesson" value="${settings.get(5)}"/><br/>
        </c:when>
        <c:otherwise>
            <label for="longBreakAfterLesson">Po której lekcji jest długa przerwa: </label><input type="text" name="longBreakAfterLesson" id="longBreakAfterLesson"/><br/>
            <label for="firstLessonStartTime">O której godzinie zaczyna się pierwsza lekcja:</label> <input type="time" name="firstLessonStartTime" id="firstLessonStartTime"/><br/>
            <label for="breakAfter45minLesson">Ile wynosi przerwa  dla lekcji 45 min:</label> <input type="time" name="breakAfter45minLesson" id="breakAfter45minLesson"/><br/>
            <label for="breakAfter60minLesson">Ile wynosi przerwa dla lekcji 60 min:</label> <input type="time" name="breakAfter60minLesson" id="breakAfter60minLesson"/><br/>
            <label for="longBreakFor45minLesson">Ile wynosi długa przerwa dal lekcji 45 min: </label><input type="time" name="longBreakFor45minLesson" id="longBreakFor45minLesson"/><br/>
            <label for="longBreakFor60minLesson">Ile wynosi długa przerwa dal lekcji 60 min: </label><input type="time" name="longBreakFor60minLesson" id="longBreakFor60minLesson"/><br/>
        </c:otherwise>
    </c:choose>

    <input type="submit" value="Submit"/>
</form>
</body>
</html>
