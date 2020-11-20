package project.inz.scheduleChecker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.inz.scheduleChecker.model.Class;
import project.inz.scheduleChecker.model.Plan;
import project.inz.scheduleChecker.model.Teacher;
import project.inz.scheduleChecker.model.TopicWithHoursQuantity;
import project.inz.scheduleChecker.service.ClassService;
import project.inz.scheduleChecker.service.PlanService;
import project.inz.scheduleChecker.service.TeacherService;
import project.inz.scheduleChecker.service.TopicWithHoursQuantityService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ClassController {

    private final ClassService classService;
    private final PlanService planService;
    private final TeacherService teacherService;
    private final TopicWithHoursQuantityService topicWithHoursQuantityService;

    public ClassController(ClassService classService, PlanService planService, TeacherService teacherService, TopicWithHoursQuantityService topicWithHoursQuantityService) {
        this.classService = classService;
        this.planService = planService;
        this.teacherService = teacherService;
        this.topicWithHoursQuantityService = topicWithHoursQuantityService;
    }

    @ModelAttribute("teachers")
    public List<Teacher> teachers() {
        return teacherService.findAll();
    }

    @GetMapping("/addClass")
    public String addClass(Model model) {
        log.warn("{}", classService.findAllWithTopics().size());
        try{
            model.addAttribute("className",classService.findAllWithTopics().get(0).getName());
        }catch(IndexOutOfBoundsException exception){
            model.addAttribute("className","empty");
        }
        return "/add/class.jsp";
    }

    @PostMapping("/addClass")
    public String addClass(HttpServletResponse response,
                           @RequestParam String name,
                           @RequestParam String arabicName,
                           @RequestParam long teacherId,
                           @RequestParam int lessonsHoursQuantity) {
        try {
            List<TopicWithHoursQuantity> topicsList = new ArrayList<>();
            Class clas = new Class(name,arabicName,teacherService.findTeacherById(teacherId),lessonsHoursQuantity, topicsList);
            if(planService.findPlanByClassOpt(name).isEmpty()){
                classService.save(clas);
                planService.save(new Plan(clas));
            }else{
                classService.save(clas);
            }

            Cookie addedClassName = new Cookie("addedClassName", name);
            response.addCookie(addedClassName);
            return "redirect:/addClass";
        } catch (DataIntegrityViolationException e) {
            System.out.println("Double teacher initials " + e.getMessage());
        }
        return "/add/duplicateEntryError.jsp";
    }

    @PostMapping("/addTopicWithHoursQuantity")
    public String addTopicWithHoursQuantityToClass(HttpServletRequest request,
                                                   @RequestParam String topic,
                                                   @RequestParam int hours) {
        log.warn("{},{}",topic,hours);
        TopicWithHoursQuantity topicWithHoursQuantity = new TopicWithHoursQuantity(topic,hours);
        String addedClassName = null;
        try {
            topicWithHoursQuantityService.save(topicWithHoursQuantity);
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals("addedClassName")){
                    addedClassName=cookie.getValue();   
                }
            }
            Class clas = classService.findClassByName(addedClassName);

            log.warn(clas.getTopicsWithHoursQuantities().toString());

            List<TopicWithHoursQuantity> topicsList = clas.getTopicsWithHoursQuantities();
            topicsList.add(topicWithHoursQuantity);
            clas.setTopicsWithHoursQuantities(topicsList);
            classService.update(clas);


            log.warn(topicWithHoursQuantity.toString());
            log.warn(clas.toString());
            log.warn(clas.getTopicsWithHoursQuantities().toString());

            return "redirect:/addClass";
        } catch (DataIntegrityViolationException e) {
            System.out.println("Double teacher initials " + e.getMessage());
        }
        return "/add/duplicateEntryError.jsp";
    }
}
