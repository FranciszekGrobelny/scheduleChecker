package project.inz.scheduleChecker.repository;

import org.springframework.transaction.annotation.Transactional;
import project.inz.scheduleChecker.model.Plan;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Transactional
public class extendedPlanRepositoryImpl implements extendedPlanRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Plan findPlanById(Long id) {
        TypedQuery<Plan> q = entityManager.createQuery("SELECT p FROM Plan p LEFT JOIN FETCH p.clas c LEFT JOIN FETCH c.topicsWithHoursQuantities  WHERE p.id=:id ",Plan.class);
    return q.setParameter("id",id).getSingleResult();
    }

    @Override
    public Plan findPlanByClass(String className) {
        TypedQuery<Plan> q = entityManager.createQuery("SELECT p FROM Plan p LEFT JOIN FETCH p.clas c LEFT JOIN FETCH c.topicsWithHoursQuantities  WHERE c.name=:className ",Plan.class);
        return q.setParameter("className",className).getSingleResult();
    }

    @Override
    public Optional<Plan> findPlanByClassOpt(String className) {
        TypedQuery<Plan> q = entityManager.createQuery("SELECT p FROM Plan p LEFT JOIN FETCH p.clas c LEFT JOIN FETCH c.topicsWithHoursQuantities  WHERE c.name=:className ",Plan.class);
        q.setParameter("className", className);
        try{
            return Optional.ofNullable(q.getSingleResult());
        }catch(NoResultException noResultException){
            return Optional.empty();
        }

    }
}
