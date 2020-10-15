package project.inz.scheduleChecker.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.inz.scheduleChecker.dto.TeacherDTO;
import project.inz.scheduleChecker.service.TeacherService;

@Controller
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/addTeacher")
    public String addTeacher(Model model){
        model.addAttribute(new TeacherDTO());
        model.addAttribute("allTeachersList",teacherService.findAll());
        return "/add/teachers.jsp";
    }

    @PostMapping("/addTeacher")
    public String addTeacher(@ModelAttribute TeacherDTO teacherDTO){
        try{
            teacherService.save(teacherDTO);
            return "redirect:/addTeacher";
        }catch (DataIntegrityViolationException e){
            System.out.println("Double teacher initials "+e.getMessage());
        }
        return "/add/duplicateEntryError.jsp";
    }
}
