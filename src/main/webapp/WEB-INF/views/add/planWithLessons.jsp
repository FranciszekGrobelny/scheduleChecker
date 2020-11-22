<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj lekcje do planu</title>
    <style> <%@include file="../../CSS/planAndLessonStyle.css"%></style>
    <script>
        function togglePopup(){
            document.getElementById("background").classList.toggle("active");
        }
    </script>
</head>
<body>
<c:forEach items="${allClasses}" var="class">
    <a href="/addLesson?klasa=${class.name}">klasa ${class.name}</a><br>
</c:forEach>
<div class="TableWithHeader">
    <div class="tableHeader">
        <h2>${className}</h2>
    </div>
    <div id="table">

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
    <%--                Zajęcia Łączone?<input type="checkbox" name="connected" ><br/>--%>
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
                    <input type="submit" value="Dodaj lekcję">
                </form>
            </td>
        </c:forEach>
        </tr>
    </c:forEach>
    </table>
</div>
<form method="post" action="/check">
    <button type="submit" onclick="togglePopup()" >Sprawdź poprawność</button>
</form>

<button onclick="togglePopup()" >pop up</button>
<div class="popup">
    <div class="background" id="background">
        <div class="content">
            <p>wiadomość z popupa!</p>
            <p>wiadomość z popupa!jshgdfjksdgsdhjgjksdhgfsdjkhgsdjkhgfsdjkyfgfkjgh</p>
            <p>wiadomość z popupa!</p><br/>
            <button id="popupButton" onclick="togglePopup()" >zamknij</button>
        </div>
    </div>
</div>
<%--<script src="<c:url value= "/resources/js/lesson.js"/>"></script>--%>
</body>
</html>