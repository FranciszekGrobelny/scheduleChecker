<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj klasę</title>
</head>
<body>
<c:if test="${className != 'empty'}">
    <a href="/addLesson?klasa=${className}">Dodaj plany dla klas</a>
</c:if>

<form method="POST" >
    nazwa klasy rzymskimi: <input type="text" name="name"/><br>
    nazwa klasa arabskimi: <input type="text" name="arabicName"/><br>
    główny nauczyciel:
    <select name="teacherId" >
        <c:forEach items="${teachers}" var="teacher">
            <option value="${teacher.id}">${teacher.initialLetters}</option>
        </c:forEach>
    </select><br>
    liczba godzin do wyrobienia: <input type="number" name="lessonsHoursQuantity"/><br>
    <input type="submit" />
    <sec:csrfInput/>
</form>

<form method="POST"  action="addTopicWithHoursQuantity">
    topic: <input name="topic"/><br>
    liczba godzin: <input name="hours"/><br>
    <input type="submit" />
</form>

<c:forEach var="class" items="${allClassesList}">
    <table>
        <tr>${class.name}</tr>
    </table>
</c:forEach>

</body>
</html>