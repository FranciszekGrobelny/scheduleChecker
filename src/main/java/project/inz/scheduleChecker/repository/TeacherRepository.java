package project.inz.scheduleChecker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.inz.scheduleChecker.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher getTeacherByName(String name);
}
