<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj ustawienia</title>
</head>
<body>
<form method="POST" >
    <label for="firstLessonStartTime">O której godzinie zaczyna się pierwsza lekcja:</label> <input type="time" name="firstLessonStartTime" id="firstLessonStartTime"/><br/>
    <label for="breakAfter45minLesson">Ile wynosi przerwa  dla lekcji 45 min:</label> <input type="time" name="breakAfter45minLesson" id="breakAfter45minLesson"/><br/>
    <label for="breakAfter60minLesson">Ile wynosi przerwa dla lekcji 60 min:</label> <input type="time" name="breakAfter60minLesson" id="breakAfter60minLesson"/><br/>
    <label for="longBreakAfterLesson">Po której lekcji jest długa przerwa: </label><input type="text" name="longBreakAfterLesson" id="longBreakAfterLesson"/><br/>
    <label for="longBreakFor45minLesson">Ile wynosi długa przerwa dal lekcji 45 min: </label><input type="time" name="longBreakFor45minLesson" id="longBreakFor45minLesson"/><br/>
    <label for="longBreakFor60minLesson">Ile wynosi długa przerwa dal lekcji 60 min: </label><input type="time" name="longBreakFor60minLesson" id="longBreakFor60minLesson"/><br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
