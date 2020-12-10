$(function () {
    console.log("js file is working");
    $('a').style.fontSize = "30px";
    var url = 'http://localhost:8080/addLesson';
    var subButton = $('.lessonSubButton');
    var lesson = function(revalidationCheckBox, topic, room, teacher, day , lessonNumber){
        this.revalidationCheckBox = revalidationCheckBox;
        this.topic = topic;
        this.room = room;
        this.teacher = teacher;
        this.day = day;
        this.lessonNumber = lessonNumber;
    };

    subButton.submit(function (e) {
        e.preventDefault();
        var form = subButton.parent();
        var newLesson = new lesson(
            form.find('.revalidation').val(),
            form.find('.topic').val(),
            form.find('.room').val(),
            form.find('.teacher').val(),
            form.find('.day').val(),
            form.find('.lessonNumber').val()
        );
        console.log('post poszedł z ajaxa');
        sendLessonToController(url,newLesson);
    });


    function sendLessonToController(url,lesson){
        $.ajax({
            url: url,
            data: JSON.stringify(lesson),
            type:'POST',
            dataType: 'json',
            contentType: 'application/json',
            success: function (result) {
                if(result.status=="success"){
                    console.log("Sukces");
                }
            },
            error: function (e) {
                console.log("error: ",e);
            }
        });
    }

});