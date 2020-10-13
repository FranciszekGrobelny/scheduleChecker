package project.inz.scheduleChecker.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter @Setter @ToString
public class SettingDTO {

    @NotNull
    private int LongBreakAfterLesson;

    @NotNull
    private LocalTime breakAfter45minLesson;

    @NotNull
    private LocalTime breakAfter60minLesson;

    @NotNull
    private LocalTime LongBreakFor45minLesson;

    @NotNull
    private LocalTime LongBreakFor60minLesson;

    @NotNull
    private LocalTime firstLessonStartTime;

    public SettingDTO(){}

    public SettingDTO(@NotNull int longBreakAfterLesson, @NotNull LocalTime breakAfter45minLesson, @NotNull LocalTime breakAfter60minLesson, @NotNull LocalTime longBreakFor45minLesson, @NotNull LocalTime longBreakFor60minLesson, @NotNull LocalTime firstLessonStartTime) {
        LongBreakAfterLesson = longBreakAfterLesson;
        this.breakAfter45minLesson = breakAfter45minLesson;
        this.breakAfter60minLesson = breakAfter60minLesson;
        LongBreakFor45minLesson = longBreakFor45minLesson;
        LongBreakFor60minLesson = longBreakFor60minLesson;
        this.firstLessonStartTime = firstLessonStartTime;
    }
}
