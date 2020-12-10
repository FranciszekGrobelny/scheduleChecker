<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
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
                        Rewalidacja?<input class="revalidation" type="checkbox" name="revalidationLesson" ><br/>
        <%--                Zajęcia Łączone?<input type="checkbox" name="connected" ><br/>--%>
                        <select class="topic" name="topic" >
                            <c:forEach items="${topics}" var="topic">
                                <option value="${topic}">${topic}</option>
                            </c:forEach>
                        </select><br>
                        Numer sali  <input class="room" type="number" name="room" ><br/>
                        <select class="teacher" name="teacherId" >
                            <c:forEach items="${teachers}" var="teacher">
                                <option value="${teacher.id}">${teacher.initialLetters}</option>
                            </c:forEach>
                        </select><br>
                        <input class="day" type="hidden" name="dayId" value="${i}">
                        <input class="lessonNumber" type="hidden" name="lessonNumber" value="${j}">
                        <button type="submit" class="lessonSubButton" data-ajax="POST">Dodaj nowa kasiazke </button>
                        <sec:csrfInput/>
                    </form>
                </td>
            </c:forEach>
            </tr>
        </c:forEach>
        </table>
    </div>
</div>
<form method="post" action="/check">
    <button type="submit">Sprawdź poprawność</button>
    <sec:csrfInput/>
</form>

<button onclick="togglePopup()" >pop up</button>
<div class="popup">
    <div class="background" id="background">
        <div class="content">
            <textarea id="popupTextarea">
                <c:if test="${areRoomsCorrect==false}">
                    Sale zostały źle dobrane w planach. Nakładają się!
                </c:if>
                <c:if test="${areTeachersCorrect==false}">
                    Godziny lekcyjne nauczycieli nakłądają się. Sprawdź to!
                </c:if>
                <c:forEach items="${teachersText}" var="text">
                    ${text}
                </c:forEach>
                <c:forEach items="${classesText}" var="text">
                    ${text}
                </c:forEach>
            </textarea>
            <button id="popupButton" onclick="togglePopup()" >zamknij</button>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/jquery-3.5.1.min.js"/>" ></script>
<%--<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" ></script>--%>
<script type="text/javascript" src="<c:url value= "/resources/js/sendLessonInfoToController.js"/>"></script>
</body>
</html>