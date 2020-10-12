package project.inz.scheduleChecker.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import project.inz.scheduleChecker.model.Teacher;
import project.inz.scheduleChecker.repository.TeacherRepository;

public class TeacherConverter implements Converter<String,Teacher> {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher convert(String source) {

        return teacherRepository.getTeacherByName(source);
    }

}
