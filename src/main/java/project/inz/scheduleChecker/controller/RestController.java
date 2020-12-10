package project.inz.scheduleChecker.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.inz.scheduleChecker.model.RestLesson;

@org.springframework.web.bind.annotation.RestController
@Slf4j
public class RestController {

    @PostMapping(value = "/addLessonRest")
    public void postLesson(@RequestBody RestLesson restLesson) {
        log.warn("Info z ajaxa {}, {}",restLesson.getRoom(), restLesson.getTeacherId());
    }
}