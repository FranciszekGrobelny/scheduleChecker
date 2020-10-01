package project.inz.scheduleChecker.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.inz.scheduleChecker.model.Setting;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class SettingDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Setting findById(Long id){
        return entityManager.find(Setting.class, id);
    }

    public void save(Setting setting){
        entityManager.persist(setting);
    }

    public void update(Setting setting){
        entityManager.merge(setting);
    }

    public void delete(Setting setting){
        entityManager.remove(entityManager.contains(setting) ? setting : entityManager.merge(setting));
    }
}
