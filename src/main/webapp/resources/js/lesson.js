document.addEventListener("DOMContentLoaded", function() {

    function addLessonInTable(){
        console.log("yo przycisk dziala");
    }
    let buttonAddLessons = document.getElementById("addLessons");
    buttonAddLessons.addEventListener("click", addLessonInTable());
});