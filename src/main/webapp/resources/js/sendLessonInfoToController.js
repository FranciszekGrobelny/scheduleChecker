$(function () {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    var url = 'http://localhost:8080/addLessonRest';
    var subButton = $('.lessonSubButton');

    subButton.on('click',function (e) {
        e.preventDefault();
        var form = $(this).parent();
        var newLesson = {
            revalidation: form.find('.revalidation').is(':checked'),
            topic: form.find('.topic').val(),
            room: form.find('.room').val(),
            teacherId: form.find('.teacher').val(),
            day: form.find('.day').val(),
            lessonNumber: form.find('.lessonNumber').val()
        };
        sendLessonToController(url,newLesson);
    });


    function sendLessonToController(url,data){
        $.ajax({
            type:'POST',
            url: url,
            data:JSON.stringify(data),
            //dataType: 'json',
            contentType: 'application/json; charset=utf-8',
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