package project.inz.scheduleChecker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.inz.scheduleChecker.dto.ClassDTO;
import project.inz.scheduleChecker.dto.TopicWithHoursQuantityDTO;
import project.inz.scheduleChecker.model.Teacher;
import project.inz.scheduleChecker.service.ClassService;
import project.inz.scheduleChecker.service.TeacherService;

import javax.validation.Valid;
import java.util.List;

@Controller @Slf4j
public class ClassController {

    private final ClassService classService;
    private final TeacherService teacherService;

    public ClassController(ClassService classService, TeacherService teacherService) {
        this.classService = classService;
        this.teacherService = teacherService;
    }

    @ModelAttribute("teachers")
    public List<Teacher> teachers() {
        return teacherService.findAll();
    }

    @GetMapping("/addClass")
    public String addClass(Model model){
        model.addAttribute(new ClassDTO());
        model.addAttribute(new TopicWithHoursQuantityDTO());
        model.addAttribute("allClassesList",classService.findAll());
        return "/add/class.jsp";
    }

    @PostMapping("/addClass")
    public String addClass(@ModelAttribute @Valid ClassDTO classDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.warn(bindingResult.toString());
            return "/index.jsp";
        }
        try{
            classService.save(classDTO);
            return "redirect:/addClass";
        }catch (DataIntegrityViolationException e){
            System.out.println("Double teacher initials "+e.getMessage());
        }
        return "/add/duplicateEntryError.jsp";
    }
}
