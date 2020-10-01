package project.inz.scheduleChecker.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import project.inz.scheduleChecker.model.Class;
import project.inz.scheduleChecker.model.Day;
import project.inz.scheduleChecker.model.Lesson;

@Getter @Setter @ToString
public class PlanDTO {

    @NotNull
    private Class clas;

    @NotNull
    private Lesson lesson;

    @NotNull
    private Day day;

    @NotNull
    private String lessonNumber;
}
