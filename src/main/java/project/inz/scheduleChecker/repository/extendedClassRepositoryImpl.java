package project.inz.scheduleChecker.repository;

import org.springframework.transaction.annotation.Transactional;
import project.inz.scheduleChecker.model.Class;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
public class extendedClassRepositoryImpl implements extendedClassRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Class findClassByName(String name) {
        TypedQuery<Class> q = entityManager.createQuery("SELECT c FROM Class c LEFT JOIN FETCH c.topicsWithHoursQuantities  WHERE c.name=:name ",Class.class);
        return q.setParameter("name", name).getSingleResult();
    }

    @Override
    public List<Class> findAllWithTopics() {
        TypedQuery<Class> q = entityManager.createQuery("SELECT DISTINCT c FROM Class c LEFT JOIN FETCH c.topicsWithHoursQuantities",Class.class);
        return q.getResultList();
    }
}
