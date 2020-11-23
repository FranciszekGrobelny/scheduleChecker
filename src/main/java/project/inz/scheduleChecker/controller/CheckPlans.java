package project.inz.scheduleChecker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.inz.scheduleChecker.model.Class;
import project.inz.scheduleChecker.model.Lesson;
import project.inz.scheduleChecker.model.Teacher;
import project.inz.scheduleChecker.model.TopicWithHoursQuantity;
import project.inz.scheduleChecker.service.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class CheckPlans {

    private final LessonService lessonService;
    private final TeacherService teacherService;
    private final ClassService classService;

    public CheckPlans(LessonService lessonService,TeacherService teacherService,ClassService classService) {
        this.lessonService = lessonService;
        this.teacherService = teacherService;
        this.classService = classService;
    }

    @PostMapping("/check")
    public String checkPlan(HttpServletRequest request,
                            RedirectAttributes redirectAttributes){
        boolean areRoomsCorrect = true;
        boolean areTeachersCorrect = true;
        String className = null;
        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals("className")){
                className=cookie.getValue();
            }
        }
        for(long i = 1;i<=5;i++){
            if(!areRoomsInPlanCorrectlyChosen(i)){
                areRoomsCorrect = false;
            }
        }
        for(long i = 1;i<=5;i++){
            if(!areTeachersInPlanCorrectlyChosen(i)){
                areTeachersCorrect = false;
            }
        }

        log.warn("czy plany dla sal jest git? {} ",areRoomsCorrect);
        log.warn("czy plany dla nauczycieli jest git? {} ",areTeachersCorrect);
        log.warn("czy lekcje dla nauczycieli sa dobrze dodane? {} ",returnTextForTeachersWhoHaveNotCorrectCompletedLessonsHoursOrCorrectText());
        log.warn("czy lekcje dla klas sa dobrze dodane? {} ",returnTextForClassesWhereLessonsHoursAreNotCompletedOrCorrectText());

        redirectAttributes.addFlashAttribute("areRoomsCorrect",areRoomsCorrect);
        redirectAttributes.addFlashAttribute("areTeachersCorrect",areTeachersCorrect);
        redirectAttributes.addFlashAttribute("teachersText",returnTextForTeachersWhoHaveNotCorrectCompletedLessonsHoursOrCorrectText());
        redirectAttributes.addFlashAttribute("classesText",returnTextForClassesWhereLessonsHoursAreNotCompletedOrCorrectText());

        return "redirect:/addLesson?klasa="+className;
    }


    // dla wszystkich lekcji jednego dnia sa sprawdzane sale czy się nakłądają czy są dobrze dobrane
    private boolean areRoomsInPlanCorrectlyChosen(Long dayId){
        boolean isCorrect=true;
        if(lessonService.findLessonsByDayIdOrderByRoomsAndStartTime(dayId).size()!=0){
            List<Lesson> lessons = lessonService.findLessonsByDayIdOrderByRoomsAndStartTime(dayId);         //  0 1 2 3 4 5
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
        List<Lesson> lessons = lessonService.findLessonsByDayIdOrderByTeachersNameAndStartTime(dayId);
        log.warn("lekcje dla nauczycieli{}", lessonService.findLessonsByDayIdOrderByTeachersNameAndStartTime(dayId).size());
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
    // zwarany tekst dla nauczycieli którzy mają za mało lub za dużo lekcji przypisanych
    private List<String> returnTextForTeachersWhoHaveNotCorrectCompletedLessonsHoursOrCorrectText(){
        List<String> teachersWithNoCorrectCompletedDiagram = new ArrayList<>();
        List<Teacher> allTeachers = teacherService.findAll();
        for(Teacher t : allTeachers){
            if(t.getHours()!=lessonService.getNumberOfLessonsForTeacherWithId(t.getId())){
                teachersWithNoCorrectCompletedDiagram.add("Nauczyciel "+t.getInitialLetters()+" powinien mieć "+t.getHours()+" lekcji,a ma "+lessonService.getNumberOfLessonsForTeacherWithId(t.getId()));
            }
        }
        if(teachersWithNoCorrectCompletedDiagram.isEmpty()){
            teachersWithNoCorrectCompletedDiagram.add("Wszyscy nauczyciele mają wypełnione poprawnie grafiki");
        }
        return teachersWithNoCorrectCompletedDiagram;
    }
    // zwracany jest tekst dla klas gdzie liczba lekcji jest zle wpisana
    private List<String> returnTextForClassesWhereLessonsHoursAreNotCompletedOrCorrectText(){
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
        if(classesWithNoCompletedDiagram.isEmpty()){
            classesWithNoCompletedDiagram.add("Plany zostały poprawnie ułożone dla wszystkich klas");
        }
        return classesWithNoCompletedDiagram;
    }

}
