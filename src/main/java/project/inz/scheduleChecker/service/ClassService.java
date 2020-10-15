package project.inz.scheduleChecker.service;

import org.springframework.stereotype.Service;
import project.inz.scheduleChecker.dto.ClassDTO;
import project.inz.scheduleChecker.model.Class;
import project.inz.scheduleChecker.model.Teacher;
import project.inz.scheduleChecker.repository.ClassRepository;

import java.util.List;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public void save(ClassDTO classDTO){
        Class clas = createClassFromClassDTO(classDTO);
        classRepository.save(clas);
    }

    public List<Class> findAll(){
        return classRepository.findAll();
    }

    public void update(ClassDTO classDTO){
        Class clas = createClassFromClassDTO(classDTO);
        classRepository.save(clas);
    }

    public void delete(Long id){
        classRepository.deleteById(id);
    }

    private Class createClassFromClassDTO(ClassDTO classDTO) {
        Class clas = new Class();
        clas.setArabicName(classDTO.getArabicName());
        clas.setLessonsHoursQuantity(classDTO.getLessonsHoursQuantity());
        clas.setMainTeacher(classDTO.getMainTeacher());
        clas.setName(classDTO.getName());
//        clas.setTopicsWithHoursQuantities(classDTO.getTopicsWithHoursQuantities());

        return clas;
    }
}
