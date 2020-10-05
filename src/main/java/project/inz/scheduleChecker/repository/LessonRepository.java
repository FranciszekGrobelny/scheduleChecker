package project.inz.scheduleChecker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.inz.scheduleChecker.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
