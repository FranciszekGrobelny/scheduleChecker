package project.inz.scheduleChecker.repository;

import project.inz.scheduleChecker.model.Day;
import project.inz.scheduleChecker.model.Lesson;

import java.util.List;

public interface extendedLessonRepository {
    List<Lesson> findAllLessonsByDayWhereLessonNumberIsLoverThan(Day day, int lessonNumber);
    List<Lesson> findLessonsByDayId(Long dayId);
}
