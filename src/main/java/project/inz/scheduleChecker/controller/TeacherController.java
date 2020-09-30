package project.inz.scheduleChecker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import project.inz.scheduleChecker.model.Teacher;

@Controller
public class TeacherController {

    @GetMapping("/addTeacher")
    public String addTeacher(@ModelAttribute Teacher teacher){

        return "/add/teachers.jsp";
    }
}
