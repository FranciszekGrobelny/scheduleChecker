package project.inz.scheduleChecker.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import project.inz.scheduleChecker.model.Teacher;
import project.inz.scheduleChecker.model.TopicWithHoursQuantity;

import java.util.List;

@Getter @Setter @ToString
public class ClassDTO {

    @NotNull
    private String name;

    @NotNull
    private String arabicName;

    @NotNull
    private Teacher mainTeacher;

    @NotNull
    private int lessonsHoursQuantity;

    @NotNull
    private List<TopicWithHoursQuantity> topicWithHoursQuantities;
}
