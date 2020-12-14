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
    private LocalTime shortBreak;
    private LocalTime longBreak;
    private LocalTime firstLessonStartTime;

    public Setting() {
    }
    public Setting(int longBreakAfterLesson,LocalTime shortBreak,LocalTime longBreak,LocalTime firstLessonStartTime) {
        this.longBreakAfterLesson = longBreakAfterLesson;
        this.shortBreak = shortBreak;
        this.longBreak = longBreak;
        this.firstLessonStartTime = firstLessonStartTime;
    }
}
