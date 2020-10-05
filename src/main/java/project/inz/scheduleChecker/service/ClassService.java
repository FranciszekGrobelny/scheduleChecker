package project.inz.scheduleChecker.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.inz.scheduleChecker.dto.ClassDTO;
import project.inz.scheduleChecker.model.Class;
import project.inz.scheduleChecker.repository.ClassRepository;

import java.util.List;

@Service @Slf4j
public class ClassService {

    private ClassRepository classRepository;

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
        clas.setTopicWithHoursQuantities(classDTO.getTopicWithHoursQuantities());

        return clas;
    }
}
