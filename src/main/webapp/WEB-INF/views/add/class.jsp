<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj klasę</title>
</head>
<body>
<form:form method="POST" modelAttribute="classDTO">
    nazwa klasy rzymskimi: <form:input path="name"/><br>
    nazwa klasa arabskimi: <form:input path="arabicName"/><br>
    główny nauczyciel: <form:input path="mainTeacher"/><br>
    liczba godzin do wyrobienia: <form:input path="lessonsHoursQuantity"/><br>
    główny nauczyciel: <form:input path="topicWithHoursQuantities"/><br>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
