<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj lekcje do planu</title>
<%--    <link rel="stylesheet" type="text/css" href="<c:url value= "/resources/CSS/planAndLessonStyle.css"/>">--%>
    <style>
        table, th, td {
            border:  solid black 3px;
        }
        .popup .overlay{
            position: fixed;
            display: block;
        }
        .popup .content{
            position: absolute;
            display: none;
        }
        .popup.active .overlay{
            display: none;
        }
        .popup.active .content{
            display: block;
        }
    </style>
    <script>
        function togglePopup(){
            document.getElementById("popup").classList.toggle("active");
        }
    </script>
</head>
<body>
<c:forEach items="${allClasses}" var="class">
    <a href="/addLesson?klasa=${class.name}">klasa ${class.name}</a><br>
</c:forEach>

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
                Zajęcia Łączone?<input type="checkbox" name="connected" ><br/>
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
                <input type="submit" placeholder="Dodaj lekcję">
            </form>
        </td>
    </c:forEach>
    </tr>
</c:forEach>
</table>
<form method="post" action="/check">
    <button type="submit" onclick="togglePopup()" >Sprawdź poprawność</button>
</form>

<button onclick="togglePopup()" >pop up</button>
<div class="popup" id="popup">
    <div class="overlay">
    </div>
    <div class="content">
        wiadomość z popupa!
        <button onclick="togglePopup()" >zamknij</button>
    </div>
</div>
<%--<script src="<c:url value= "/resources/js/lesson.js"/>"></script>--%>
</body>
</html>
