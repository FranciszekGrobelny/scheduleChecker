package project.inz.scheduleChecker.repository;

import project.inz.scheduleChecker.model.Day;
import project.inz.scheduleChecker.model.Lesson;

import java.util.List;

public interface extendedLessonRepository {
    List<Lesson> findAllLessonsByDayWhereLessonNumberIsLoverThan(Day day, int lessonNumber);
    List<Lesson> findLessonsByDayIdOrderByRoomsAndStartTime(Long dayId);
    List<Lesson> findLessonsByDayIdOrderByTeachersNameAndStartTime(Long dayId);
    Long getNumberOfLessonsForTeacherWithId(Long teacherId);
    Long getNumberOfLessonsForClassAndTopic(String className, String topic);

}
