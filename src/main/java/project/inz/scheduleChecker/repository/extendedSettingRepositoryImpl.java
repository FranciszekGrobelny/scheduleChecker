package project.inz.scheduleChecker.repository;

import org.springframework.transaction.annotation.Transactional;
import project.inz.scheduleChecker.model.Setting;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public class extendedSettingRepositoryImpl implements  extendedSettingRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Setting findFirst() {
        return entityManager.createQuery("SELECT s FROM Setting s",Setting.class).getSingleResult();
    }
}
