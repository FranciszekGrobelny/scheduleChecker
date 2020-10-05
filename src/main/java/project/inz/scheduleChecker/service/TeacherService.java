package project.inz.scheduleChecker.service;

import org.springframework.stereotype.Service;
import project.inz.scheduleChecker.dto.TeacherDTO;
import project.inz.scheduleChecker.model.Teacher;
import project.inz.scheduleChecker.repository.TeacherRepository;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void save(TeacherDTO teacherDTO){
        Teacher teacher = createClassFromClassDTO(teacherDTO);
        teacherRepository.save(teacher);
    }

    public List<Teacher> findAll(){
        return teacherRepository.findAll();
    }

    public void update(TeacherDTO teacherDTO){
        Teacher teacher = createClassFromClassDTO(teacherDTO);
        teacherRepository.save(teacher);
    }

    public void delete(Long id){
        teacherRepository.deleteById(id);
    }

    private Teacher createClassFromClassDTO(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setInitialLetters(teacherDTO.getInitialLetters());
        teacher.setHours(teacherDTO.getHours());
        teacher.setIsSpecialist(teacherDTO.getIsSpecialist());

        return teacher;
    }
}
