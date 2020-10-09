package project.inz.scheduleChecker.converter;

import org.springframework.beans.factory.annotation.Autowired;
import project.inz.scheduleChecker.model.Teacher;
import project.inz.scheduleChecker.repository.TeacherRepository;
import project.inz.scheduleChecker.service.TeacherService;

public class StringToTeacherConverter implements Converter<Teacher,String>{

    @Autowired
    TeacherService teacherService;

    @Override
    public Teacher convert(String source) {
        return teacherService.getTeacherByName(source);
    }
}
