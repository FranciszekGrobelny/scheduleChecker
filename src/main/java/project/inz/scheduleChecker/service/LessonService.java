package project.inz.scheduleChecker.service;

import org.springframework.stereotype.Service;
import project.inz.scheduleChecker.model.Day;
import project.inz.scheduleChecker.model.Lesson;
import project.inz.scheduleChecker.repository.LessonRepository;

import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public void save(Lesson lesson){
        lessonRepository.save(lesson);
    }

    public List<Lesson> findAll(){
        return lessonRepository.findAll();
    }

    public List<Lesson> findLessonsByDayIdOrderByRoomsAndStartTime(Long dayId){
        return lessonRepository.findLessonsByDayIdOrderByRoomsAndStartTime(dayId);
    }
    public List<Lesson> findLessonsByDayIdOrderByTeachersNameAndStartTime(Long dayId){
        return lessonRepository.findLessonsByDayIdOrderByTeachersNameAndStartTime(dayId);
    }

    public List<Lesson> findAllLessonsByDayWhereLessonNumberIsLoverThan(Day day, int lessonNumber){ return lessonRepository.findAllLessonsByDayWhereLessonNumberIsLoverThan(day,lessonNumber);}

    public Long getNumberOfLessonsForTeacherWithId(Long teacherId){return lessonRepository.getNumberOfLessonsForTeacherWithId(teacherId);}
    public void update(Lesson lesson){
        lessonRepository.save(lesson);
    }

    public void delete(Long id){
        lessonRepository.deleteById(id);
    }

    public Long getNumberOfLessonsForClassAndTopic(String className, String topic){return lessonRepository.getNumberOfLessonsForClassAndTopic(className,topic);}



}
