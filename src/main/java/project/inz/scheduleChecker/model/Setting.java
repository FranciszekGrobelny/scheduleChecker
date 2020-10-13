package project.inz.scheduleChecker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "settings")
@Getter @Setter @ToString
public class Setting extends ParentEntity{

    private int longBreakAfterLesson;
    private LocalTime breakAfter45minLesson;
    private LocalTime breakAfter60minLesson;
    private LocalTime LongBreakFor45minLesson;
    private LocalTime LongBreakFor60minLesson;
    private LocalTime firstLessonStartTime;
}
