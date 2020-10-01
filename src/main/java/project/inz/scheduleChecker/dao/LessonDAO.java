package project.inz.scheduleChecker.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.inz.scheduleChecker.model.Lesson;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class LessonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Lesson findById(Long id){
        return entityManager.find(Lesson.class, id);
    }

    public void save(Lesson lesson){
        entityManager.persist(lesson);
    }

    public void update(Lesson lesson){
        entityManager.merge(lesson);
    }

    public void delete(Lesson lesson){
        entityManager.remove(entityManager.contains(lesson) ? lesson : entityManager.merge(lesson));
    }
}
