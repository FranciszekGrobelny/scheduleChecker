package project.inz.scheduleChecker.repository;

import org.springframework.transaction.annotation.Transactional;
import project.inz.scheduleChecker.model.Day;
import project.inz.scheduleChecker.model.Lesson;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
public class extendedLessonRepositoryImpl implements extendedLessonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Lesson> findAllLessonsByDayWhereLessonNumberIsLoverThan(Day day, int lessonNumber) {
        TypedQuery<Lesson> q = entityManager.createQuery("SELECT l FROM Lesson l LEFT JOIN FETCH l.plan p JOIN FETCH p.clas c JOIN FETCH c.topicsWithHoursQuantities WHERE l.day=:day AND l.lessonNumber<:lessonNumber",Lesson.class);
        return q.setParameter("day", day).setParameter("lessonNumber",lessonNumber).getResultList();
    }

    @Override
    public List<Lesson> findLessonsByDayIdOrderByRoomsAndStartTime(Long dayId) {
        TypedQuery<Lesson> q = entityManager.createQuery("SELECT DISTINCT l FROM Lesson l LEFT JOIN FETCH l.plan p JOIN FETCH p.clas c JOIN FETCH c.topicsWithHoursQuantities WHERE l.day.id=:dayId ORDER BY l.room, l.startTime",Lesson.class);
        return q.setParameter("dayId", dayId).getResultList();
    }

    @Override
    public List<Lesson> findLessonsByDayIdOrderByTeachersNameAndStartTime(Long dayId) {
        TypedQuery<Lesson> q = entityManager.createQuery("SELECT DISTINCT l FROM Lesson l LEFT JOIN FETCH l.plan p JOIN FETCH p.clas c JOIN FETCH c.topicsWithHoursQuantities WHERE l.day.id=:dayId ORDER BY l.teacher.id, l.startTime",Lesson.class);
        return q.setParameter("dayId", dayId).getResultList();
    }

    @Override
    public Long getNumberOfLessonsForTeacherWithId(Long teacherId) {
        Query q = entityManager.createQuery("SELECT DISTINCT COUNT(l) FROM Lesson l  WHERE l.teacher.id=:teacherId");
        return (Long) q.setParameter("teacherId", teacherId).getSingleResult();
    }
    @Override
    public Long getNumberOfLessonsForClassAndTopic(String className, String topic) {
        Query q = entityManager.createQuery("SELECT DISTINCT COUNT(l) FROM Lesson l  WHERE l.plan.clas.name=:className AND l.topic=:topic");
        return (Long) q.setParameter("className", className).setParameter("topic",topic).getSingleResult();
    }

}
