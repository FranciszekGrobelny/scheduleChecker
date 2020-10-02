package project.inz.scheduleChecker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import project.inz.scheduleChecker.model.Class;

public interface ClassRepository extends CrudRepository<Class, Long>, JpaRepository<Class, Long> {

}
