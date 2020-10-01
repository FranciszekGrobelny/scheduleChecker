package project.inz.scheduleChecker.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.inz.scheduleChecker.model.Class;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class ClassDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Class findById(Long id){
        return entityManager.find(Class.class, id);
    }

    public void save(Class clas){
        entityManager.persist(clas);
    }

    public void update(Class clas){
        entityManager.merge(clas);
    }

    public void delete(Class clas){
        entityManager.remove(entityManager.contains(clas) ? clas : entityManager.merge(clas));
    }
}
