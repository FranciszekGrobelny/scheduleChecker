<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Dodaj nauczyciela</title>
</head>
<body>
<form:form method="POST" modelAttribute="teacherDTO">
    Inicjały nauczyciela: <form:input path="initialLetters"/><br>
    Godziny do wypracowania: <form:input path="hours"/><br>
    Jest on/a specjalistą: <form:checkbox path="isSpecialist"/><br>
    <input type="submit" value="Submit"/>
</form:form>

<label>Dodani już nauczyciele:</label><br/>
<c:forEach var="teacher" items="${allTeachersList}">
    <table>
        <tr>${teacher.initialLetters}</tr>
    </table>
</c:forEach>
</body>
</html>
