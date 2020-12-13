package project.inz.scheduleChecker.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.inz.scheduleChecker.model.*;
import project.inz.scheduleChecker.service.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@Slf4j
public class RestController {

    private final PlanService planService;
    private final LessonService lessonService;
    private final TeacherService teacherService;
    private final DayService dayService;
    private final SettingService settingService;
    private final ClassService classService;

    public RestController(PlanService planService,
                              LessonService lessonService,
                              TeacherService teacherService,
                              DayService dayService,
                              SettingService settingService,
                              ClassService classService) {
        this.planService = planService;
        this.lessonService = lessonService;
        this.teacherService = teacherService;
        this.dayService = dayService;
        this.settingService = settingService;
        this.classService = classService;
    }

    @PostMapping(value = "/addLessonRest")
    public String postLesson(@RequestBody RestLesson restLesson,
                           HttpServletRequest request) {
        log.warn("Info z ajaxa {}, {}",restLesson.getRoom(), restLesson.getRevalidation());
        String className = null;
        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals("className")){
                className=cookie.getValue();
            }
        }
        Day day =dayService.findByIdNoOptional(Long.parseLong(restLesson.getDay()));
        LocalTime start = getStartTime(Integer.parseInt(restLesson.getLessonNumber()), day);
        LocalTime stop = getEndTime(start,Boolean.parseBoolean(restLesson.getRevalidation()));
        Plan plan = planService.findPlanByClass(className);
        Lesson lesson = new Lesson(
                Boolean.parseBoolean(restLesson.getRevalidation()),
                Integer.parseInt(restLesson.getLessonNumber()),
                start,
                stop,
                teacherService.findTeacherById(Long.parseLong(restLesson.getTeacherId())),
                restLesson.getTopic(),
                Integer.parseInt(restLesson.getRoom()),
                plan,
                day);
        lessonService.save(lesson);

        return "redirect:/addLesson?klasa="+className;
    }
    private LocalTime getStartTime(int lessonNumber, Day day){
        LocalTime start;
        Setting settings = settingService.findAll().get(0);
        if(lessonNumber==1){
            start = settings.getFirstLessonStartTime();
        }else{
            List<Lesson> lessons = lessonService.findAllLessonsByDayWhereLessonNumberIsLoverThan(day, lessonNumber);
            Optional<Lesson> lessonBefore = lessons.stream()
                    .sorted(Comparator.comparing(Lesson::getLessonNumber).reversed())
                    .findFirst();
            log.warn("{}",lessonBefore.toString());
            if(lessonBefore.get().isRevalidationLesson()){
                if(lessonBefore.get().getLessonNumber()==settings.getLongBreakAfterLesson()){
                    start = lessonBefore.get().getEndTime().plusMinutes(settings.getLongBreakFor60minLesson().getMinute());
                }else{
                    start = lessonBefore.get().getEndTime().plusMinutes(settings.getBreakAfter60minLesson().getMinute());
                }
            }else{
                if(lessonBefore.get().getLessonNumber()==settings.getLongBreakAfterLesson()){
                    start = lessonBefore.get().getEndTime().plusMinutes(settings.getLongBreakFor45minLesson().getMinute());
                }else{
                    start = lessonBefore.get().getEndTime().plusMinutes(settings.getBreakAfter45minLesson().getMinute());
                }
            }
        }
        return start;
    }
    private LocalTime getEndTime(LocalTime start, boolean revalidationLesson){
        LocalTime stop;
        if(revalidationLesson){
            stop = start.plusMinutes(60);
        }else{
            stop = start.plusMinutes(45);
        }
        return stop;
    }
}