package project.inz.scheduleChecker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes")
@Getter @Setter @ToString
public class Class extends ParentEntity {


    private String name;

    private String arabicName;

    @OneToOne
    private Teacher mainTeacher;

    private int lessonsHoursQuantity;

    @OneToMany
    private List<TopicWithHoursQuantity> topicWithHoursQuantities;

}