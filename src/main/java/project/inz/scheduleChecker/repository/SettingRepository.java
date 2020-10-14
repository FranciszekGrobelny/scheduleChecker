package project.inz.scheduleChecker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.inz.scheduleChecker.model.Setting;

public interface SettingRepository extends JpaRepository<Setting, Long>, extendedSettingRepository {

    void deleteAll();
    Setting findFirst();
}
