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

        log.warn("dodało się ciasteczko {}",className.getValue());
        log.warn("{}", returnListOfClassesWhereLessonsHoursAreNotCompleted());
                
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
                connected,
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

    @PostMapping("/check")
    public String checkPlan(HttpServletRequest request){
        boolean correctRooms = true;
        boolean correctTeachers = true;
        String className = null;
        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals("className")){
                className=cookie.getValue();
            }
        }
        for(long i = 1;i<=5;i++){
            if(!areRoomsInPlanCorrectlyChosen(i)){
                correctRooms = false;
            }
        }
        for(long i = 1;i<=5;i++){
            if(!areTeachersInPlanCorrectlyChosen(i)){
                correctTeachers = false;
            }
        }

        log.warn("czy plany dla sal jest git? {} ",correctRooms);
        log.warn("czy plany dla nauczycieli jest git? {} ",correctTeachers);
        return "redirect:/addLesson?klasa="+className;
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
    
    private boolean areRoomsInPlanCorrectlyChosen(Long dayId){
        boolean isCorrect=true;
        if(lessonService.findLessonsByDayIdOrderByRooms(dayId).size()!=0){
            List<Lesson> lessons = lessonService.findLessonsByDayIdOrderByRooms(dayId);                     //  0 1 2 3 4 5
            // indeksy ostatnich powtarzajacych sie sali(do którego indeksu jest ta sama sala), np. 2 2 3 3 4 5 = 1, 3, 4, 5
            List<Integer> indexesOfLastSameRooms = new ArrayList<>();


            for (int i = 0; i < lessons.size(); i++ ) {
                if(i == lessons.size()-1){
                    indexesOfLastSameRooms.add(i);
                }else{
                    if(lessons.get(i).getRoom()!=lessons.get(i+1).getRoom()){
                        indexesOfLastSameRooms.add(i);
                    }
                }
            }

            for(int i = 0, j = 0; i < lessons.size(); i++){
                if(i<indexesOfLastSameRooms.get(j)){
                    if(lessons.get(i).getStartTime().isAfter(lessons.get(i+1).getEndTime()) || lessons.get(i).getStartTime().equals(lessons.get(i+1).getStartTime()) ){
                        isCorrect = false;
                    }
                }else if(i==indexesOfLastSameRooms.get(j)){
                }else{
                    i--;
                    j++;
                }
            }
        }
        return isCorrect;
    }

    private boolean areTeachersInPlanCorrectlyChosen(Long dayId) {
        List<Lesson> lessons = lessonService.findLessonsByDayIdOrderByTeachersName(dayId);
        log.warn("lekcje dla nauczycieli{}", lessonService.findLessonsByDayIdOrderByTeachersName(dayId).size());
        List<Integer> indexesOfLastSameTeachers = new ArrayList<>();
        boolean isCorrect=true;

        for (int i = 0; i < lessons.size(); i++ ) {
            if(i == lessons.size()-1){
                indexesOfLastSameTeachers.add(i);
            }else{
                if(lessons.get(i).getTeacher().getInitialLetters()!=lessons.get(i+1).getTeacher().getInitialLetters()){
                    indexesOfLastSameTeachers.add(i);
                }
            }
        }

        for(int i = 0, j = 0; i < lessons.size(); i++){
            if(i<indexesOfLastSameTeachers.get(j)){
                if(lessons.get(i).getStartTime().isAfter(lessons.get(i+1).getEndTime()) || lessons.get(i).getStartTime().equals(lessons.get(i+1).getStartTime()) ){
                    isCorrect = false;
                }
            }else if(i==indexesOfLastSameTeachers.get(j)){
            }else{
                i--;
                j++;
            }
        }
        return isCorrect;
    }

    private List<Teacher> returnListOfTeachersWhoHaveNotCompletedLessonsHours(){
        List<Teacher> teachersWithNoCompletedDiagram = new ArrayList<>();
        List<Teacher> allTeachers = teacherService.findAll();
        for(Teacher t : allTeachers){
            if(t.getHours()!=lessonService.getNumberOfLessonsForTeacherWithId(t.getId())){
                teachersWithNoCompletedDiagram.add(t);
            }
        }
        return teachersWithNoCompletedDiagram;
    }

    private List<String> returnListOfClassesWhereLessonsHoursAreNotCompleted(){
        List<String> classesWithNoCompletedDiagram = new ArrayList<>();
        List<Class> allClasses = classService.findAllWithTopics();
        for(Class c : allClasses){
            List<TopicWithHoursQuantity> topicWithHoursQuantityList = c.getTopicsWithHoursQuantities();
            for(TopicWithHoursQuantity t : topicWithHoursQuantityList){
                if(t.getHoursQuantity()!=lessonService.getNumberOfLessonsForClassAndTopic(c.getName(),t.getTopic())){
                    classesWithNoCompletedDiagram.add("W klasie "+c.getName()+" o podanej lekcji "+t.getTopic()+" powinno być lekcji "+t.getHoursQuantity()+" a jest "+lessonService.getNumberOfLessonsForClassAndTopic(c.getName(),t.getTopic()));
                }
            }
        }
        return classesWithNoCompletedDiagram;
    }
}