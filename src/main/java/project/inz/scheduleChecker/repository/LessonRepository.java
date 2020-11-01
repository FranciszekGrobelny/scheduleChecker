package project.inz.scheduleChecker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.inz.scheduleChecker.model.Day;
import project.inz.scheduleChecker.model.Lesson;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long>, extendedLessonRepository {

    List<Lesson> findLessonsByDay(Day day);
}
