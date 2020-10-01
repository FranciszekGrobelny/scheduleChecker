package project.inz.scheduleChecker.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.inz.scheduleChecker.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class TeacherDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Teacher findById(Long id){
        return entityManager.find(Teacher.class, id);
    }

    public void save(Teacher teacher){
        entityManager.persist(teacher);
    }

    public void upgrade(Teacher teacher){
        entityManager.merge(teacher);
    }

    public void delete(Teacher teacher) {
        entityManager.remove(entityManager.contains(teacher) ? teacher : entityManager.merge(teacher));
    }
}
