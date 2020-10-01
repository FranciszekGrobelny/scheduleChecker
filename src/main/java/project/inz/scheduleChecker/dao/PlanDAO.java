package project.inz.scheduleChecker.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.inz.scheduleChecker.model.Plan;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class PlanDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Plan findById(Long id){
        return entityManager.find(Plan.class, id);
    }

    public void save(Plan plan){
        entityManager.persist(plan);
    }

    public void upgrade(Plan plan){
        entityManager.merge(plan);
    }

    public void delete(Plan plan) {
        entityManager.remove(entityManager.contains(plan) ? plan : entityManager.merge(plan));
    }
}
