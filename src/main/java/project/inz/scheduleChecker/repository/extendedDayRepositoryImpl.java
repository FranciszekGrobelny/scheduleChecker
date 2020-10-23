package project.inz.scheduleChecker.repository;

import org.springframework.transaction.annotation.Transactional;
import project.inz.scheduleChecker.model.Day;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Transactional
public class extendedDayRepositoryImpl implements extendedDayRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Day findByIdNoOptional(Long id) {
        TypedQuery<Day> q = entityManager.createQuery("SELECT d FROM Day d WHERE d.id=:id",Day.class).setParameter("id",id);
        return q.getSingleResult();
    }
}
