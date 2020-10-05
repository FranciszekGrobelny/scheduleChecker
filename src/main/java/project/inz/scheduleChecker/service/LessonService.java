package project.inz.scheduleChecker.service;

import org.springframework.stereotype.Service;
import project.inz.scheduleChecker.dto.LessonDTO;
import project.inz.scheduleChecker.model.Lesson;
import project.inz.scheduleChecker.repository.LessonRepository;

import java.util.List;

@Service
public class LessonService {

    private LessonRepository lessonRepository;

    public void save(LessonDTO lessonDTO){
        Lesson lesson = createClassFromClassDTO(lessonDTO);
        lessonRepository.save(lesson);
    }

    public List<Lesson> findAll(){
        return lessonRepository.findAll();
    }

    public void update(LessonDTO lessonDTO){
        Lesson lesson = createClassFromClassDTO(lessonDTO);
        lessonRepository.save(lesson);
    }

    public void delete(Long id){
        lessonRepository.deleteById(id);
    }

    private Lesson createClassFromClassDTO(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        lesson.setConnected(lessonDTO.getConnected());
        lesson.setType(lessonDTO.getType());
        lesson.setStartTime(lessonDTO.getStartTime());
        lesson.setEndTime(lessonDTO.getEndTime());
        lesson.setRoom(lessonDTO.getRoom());
        lesson.setTeacher(lessonDTO.getTeacher());
        lesson.setTopic(lessonDTO.getTopic());
        lesson.setTeacher2(lessonDTO.getTeacher2());
        lesson.setTopic2(lessonDTO.getTopic2());

        return lesson;
    }
}
