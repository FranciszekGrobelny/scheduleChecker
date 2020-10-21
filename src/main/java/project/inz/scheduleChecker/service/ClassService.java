package project.inz.scheduleChecker.service;

import org.springframework.stereotype.Service;
import project.inz.scheduleChecker.model.Class;
import project.inz.scheduleChecker.repository.ClassRepository;

import java.util.List;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public void save(Class clas){
        classRepository.save(clas);
    }

    public List<Class> findAll(){
        return classRepository.findAll();
    }

    public Class findClassByName(String name){
        return classRepository.findClassByName(name);
    }

    public void update(Class clas){
        classRepository.save(clas);
    }

    public void delete(Long id){
        classRepository.deleteById(id);
    }


}
