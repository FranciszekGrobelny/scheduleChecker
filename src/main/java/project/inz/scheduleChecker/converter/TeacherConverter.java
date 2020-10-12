package project.inz.scheduleChecker.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import project.inz.scheduleChecker.model.Teacher;
import project.inz.scheduleChecker.repository.TeacherRepository;

public class TeacherConverter implements Converter<String,Teacher> {


    private TeacherRepository teacherRepository;

    @Override
    public Teacher convert(String source) {
        Teacher teacher = teacherRepository.getTeacherById(Long.parseLong(source));
        if(null==teacher){
            throw new IllegalStateException("Nie ma takiego nauczyciela!");
        }
        return teacher;
    }

    @Autowired
    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
}
