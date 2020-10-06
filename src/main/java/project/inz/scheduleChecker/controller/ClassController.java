package project.inz.scheduleChecker.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.inz.scheduleChecker.dto.ClassDTO;
import project.inz.scheduleChecker.service.ClassService;

@Controller
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/addClass")
    public String addClass(Model model){
        model.addAttribute(new ClassDTO());
        return "/add/class.jsp";
    }

    @PostMapping("/addClass")
    public String addClass(@ModelAttribute ClassDTO classDTO){
        try{
            classService.save(classDTO);
            return "/add/class.jsp";
        }catch (DataIntegrityViolationException e){
            System.out.println("Double teacher initials "+e.getMessage());
        }
        return "/add/duplicateEntryError.jsp";
    }
}
