package project.inz.scheduleChecker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.inz.scheduleChecker.model.*;
import project.inz.scheduleChecker.model.Class;
import project.inz.scheduleChecker.service.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class addPlanWithLessons {

    private final PlanService planService;
    private final LessonService lessonService;
    private final TeacherService teacherService;
    private final DayService dayService;
    private final SettingService settingService;
    private final ClassService classService;

    public addPlanWithLessons(PlanService planService,
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

    @ModelAttribute("teachers")
    public List<Teacher> teachers() {
        return teacherService.findAll();
    }

    @GetMapping("/addLesson")
    public String addLesson(@RequestParam String klasa,
                            HttpServletResponse response,
                            Model model) {
        Cookie className = new Cookie("className", klasa);
        response.addCookie(className);
        Class class_ = classService.findClassByName(className.getValue());
        List<String> topics = new ArrayList<>();
        for (TopicWithHoursQuantity t: class_.getTopicsWithHoursQuantities()) {
            topics.add(t.getTopic());
        }
        model.addAttribute("topics",topics);
        model.addAttribute("allClasses", classService.findAllWithTopics());
        model.addAttribute("className", klasa);

        if(model.getAttribute("areRoomsCorrect") != null){
            model.addAttribute("areTeachersCorrect",model.getAttribute("areTeachersCorrect"));
            model.addAttribute("areRoomsCorrect",model.getAttribute("areRoomsCorrect"));
            model.addAttribute("teachersText",model.getAttribute("teachersText"));
            model.addAttribute("classesText",model.getAttribute("classesText"));
        }

        log.warn("dodało się ciasteczko {}",className.getValue());
        return "/add/planWithLessons.jsp";
    }

    @PostMapping("/addLesson")
    public String addLesson(@RequestParam Long teacherId,
                            @RequestParam(required = false) boolean revalidationLesson,
                            @RequestParam(required = false) boolean connected,
                            @RequestParam int room,
                            @RequestParam Long dayId,
                            @RequestParam int lessonNumber,
                            @RequestParam String topic,
                            HttpServletRequest request
                            ) {
        String className = null;
        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals("className")){
                className=cookie.getValue();
            }
        }
        Day day =dayService.findByIdNoOptional(dayId);
        LocalTime start = getStartTime(lessonNumber, day);
        LocalTime stop = getEndTime(start,revalidationLesson);
        Plan plan = planService.findPlanByClass(className);
        Lesson lesson = new Lesson(
                revalidationLesson,
                lessonNumber,
                start,
                stop,
                teacherService.findTeacherById(teacherId),
                topic,
                room,
                plan,
                day);
        lessonService.save(lesson);

        return "redirect:/addLesson?klasa="+className;
    }

    @PostMapping("/addPlan")
    public String addPlan() {

        return "redirect:/addLesson";
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
            if(lessonBefore.get().getLessonNumber()==6 && lessonBefore.get().isRevalidationLesson() ){
                start = lessonBefore.get().getEndTime().plusMinutes(settings.getLongBreak().getMinute());
            }else{
                start = lessonBefore.get().getEndTime().plusMinutes(settings.getShortBreak().getMinute());
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