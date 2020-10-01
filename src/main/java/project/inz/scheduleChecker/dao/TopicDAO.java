package project.inz.scheduleChecker.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.inz.scheduleChecker.model.Topic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class TopicDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Topic findById(Long id){
        return entityManager.find(Topic.class, id);
    }

    public void save(Topic topic){
        entityManager.persist(topic);
    }

    public void upgrade(Topic topic){
        entityManager.merge(topic);
    }

    public void delete(Topic topic) {
        entityManager.remove(entityManager.contains(topic) ? topic : entityManager.merge(topic));
    }
}
