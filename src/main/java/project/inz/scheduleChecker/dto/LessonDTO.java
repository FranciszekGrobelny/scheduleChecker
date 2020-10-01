package project.inz.scheduleChecker.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import project.inz.scheduleChecker.model.Teacher;
import project.inz.scheduleChecker.model.Topic;

import java.time.LocalTime;

@Getter @Setter @ToString
public class LessonDTO {

    @NotNull
    private String type;

    private String connected;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotNull
    private Teacher teacher;

    @NotNull
    private Topic topic;

    @NotNull
    private int room;


    private Teacher teacher2;


    private Topic topic2;
}
