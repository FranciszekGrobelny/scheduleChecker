<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj klasę</title>
</head>
<body>
<form:form method="POST" modelAttribute="class">
    nazwa klasy rzymskimi: <form:input path="name"/><br>
    nazwa klasa arabskimi: <form:input path="arabicName"/><br>
    główny nauczyciel: <form:select itemLabel="initialLetters" itemValue="id" items="${teachers}" path="mainTeacher.id"/><br>
    liczba godzin do wyrobienia: <form:input path="lessonsHoursQuantity"/><br>
    <input type="submit" />
</form:form>

<form:form method="POST" modelAttribute="topicWithHoursQuantity" action="/addTopicWithHoursQuantity">

    <input type="submit" />
</form:form>

<c:forEach var="class" items="${allClassesList}">
    <table>
        <tr>${class.name}</tr>
    </table>
</c:forEach>

</body>
</html>