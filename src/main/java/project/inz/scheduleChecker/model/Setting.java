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
    private LocalTime longBreakFor45minLesson;
    private LocalTime longBreakFor60minLesson;
    private LocalTime firstLessonStartTime;

    public Setting() {
    }
    public Setting(int longBreakAfterLesson,LocalTime breakAfter45minLesson,LocalTime breakAfter60minLesson,
                   LocalTime longBreakFor45minLesson, LocalTime longBreakFor60minLesson, LocalTime firstLessonStartTime) {
        this.longBreakAfterLesson = longBreakAfterLesson;
        this.breakAfter45minLesson = breakAfter45minLesson;
        this.breakAfter60minLesson = breakAfter60minLesson;
        this.longBreakFor45minLesson = longBreakFor45minLesson;
        this.longBreakFor60minLesson = longBreakFor60minLesson;
        this.firstLessonStartTime = firstLessonStartTime;
    }

}
