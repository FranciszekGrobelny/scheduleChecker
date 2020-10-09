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
    temat zajec: <form:input path="topicsWithHoursQuantities[0].topic"/>
    godziny: <form:input path="topicsWithHoursQuantities[0].hoursQuantity"/>
    <input type="submit" />
</form:form>


</body>
</html>