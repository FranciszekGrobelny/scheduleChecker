package project.inz.scheduleChecker.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.inz.scheduleChecker.model.TopicWithHoursQuantity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class TopicWithHoursQuantityDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public TopicWithHoursQuantity findById(Long id){
        return entityManager.find(TopicWithHoursQuantity.class, id);
    }

    public void save(TopicWithHoursQuantity topicWithHoursQuantity){
        entityManager.persist(topicWithHoursQuantity);
    }

    public void upgrade(TopicWithHoursQuantity topicWithHoursQuantity){
        entityManager.merge(topicWithHoursQuantity);
    }

    public void delete(TopicWithHoursQuantity topicWithHoursQuantity) {
        entityManager.remove(entityManager.contains(topicWithHoursQuantity) ? topicWithHoursQuantity : entityManager.merge(topicWithHoursQuantity));
    }
}
