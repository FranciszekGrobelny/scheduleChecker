package project.inz.scheduleChecker.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import project.inz.scheduleChecker.model.Teacher;
import project.inz.scheduleChecker.model.TopicWithHoursQuantity;

import javax.persistence.Column;
import java.util.List;

@Getter @Setter @ToString
public class ClassDTO {

    @NotNull @Column(unique = true)
    private String name;

    @NotNull  @Column(unique = true)
    private String arabicName;


    private Teacher mainTeacher;

    @NotNull
    private int lessonsHoursQuantity;

    private List<TopicWithHoursQuantity> topicsWithHoursQuantities;
}
