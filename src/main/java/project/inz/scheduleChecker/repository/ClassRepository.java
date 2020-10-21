package project.inz.scheduleChecker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.inz.scheduleChecker.model.Class;

public interface ClassRepository extends JpaRepository<Class, Long>, extendedClassRepository {

}
