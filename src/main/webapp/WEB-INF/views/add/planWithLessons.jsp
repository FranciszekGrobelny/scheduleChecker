<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj lekcje do planu</title>
</head>
<body>
<a href="/addLesson?klasa=IV">klasa IV</a>
<form method="POST" action="/addPlan">
</form>
<form method="post" action="/addLesson">
    Rewalidacja?<input type="checkbox" name="revalidationLesson" ><br/>
    Zajęcia Łączone?<input type="checkbox" name="connected" ><br/>
    Numer sali <input type="number" name="room" ><br/>
    <select name="teacherId" >
        <c:forEach items="${teachers}" var="teacher">
            <option value="${teacher.id}">${teacher.initialLetters}</option>
        </c:forEach>
    </select><br>
    <input type="submit" placeholder="Dodaj lekcję">
</form>
</body>
</html>
