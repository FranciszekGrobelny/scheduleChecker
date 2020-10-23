package project.inz.scheduleChecker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.inz.scheduleChecker.model.Day;
import project.inz.scheduleChecker.model.Lesson;
import project.inz.scheduleChecker.model.Plan;
import project.inz.scheduleChecker.model.Teacher;
import project.inz.scheduleChecker.service.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class addPlanWithLessons {

    private final PlanService planService;
    private final LessonService lessonService;
    private final TeacherService teacherService;
    private final DayService dayService;

    public addPlanWithLessons(PlanService planService, LessonService lessonService, TeacherService teacherService, DayService dayService) {
        this.planService = planService;
        this.lessonService = lessonService;
        this.teacherService = teacherService;
        this.dayService = dayService;
    }

    @ModelAttribute("teachers")
    public List<Teacher> teachers() {
        return teacherService.findAll();
    }

    @GetMapping("/addLesson")
    public String addLesson() {

        return "/add/planWithLessons.jsp";
    }

    @PostMapping("/addLesson")
    public String addLesson(@RequestParam Long teacherId,
                            @RequestParam(required = false) boolean revalidationLesson,
                            @RequestParam(required = false) boolean connected,
                            @RequestParam int room,
                            @RequestParam Long dayId) {
        Day day =dayService.findByIdNoOptional(dayId);
        log.warn("{}, {}",dayId.toString(),day.toString());
        LocalTime start = LocalTime.now();
        LocalTime stop = LocalTime.now();
        Plan plan = planService.findPlanById((long) 1);
        log.warn("{}, {}, {}, {}, {}, {}, {}",revalidationLesson,connected,start,stop,teacherService.findTeacherById(teacherId).toString(),room,plan.toString());
        Lesson lesson = new Lesson(revalidationLesson, connected, start, stop, teacherService.findTeacherById(teacherId), "Religia", room, plan, day);
        lessonService.save(lesson);

        return "redirect:/addLesson";
    }

    @PostMapping("/addPlan")
    public String addPlan() {

        return "redirect:/addLesson";
    }
}
