package project.inz.scheduleChecker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "lessons")
@Getter @Setter @ToString
public class Lesson extends ParentEntity {

    private String type;
    
    private String connected;

    private LocalTime startTime;

    private LocalTime endTime;

    @OneToOne
    private Teacher teacher;

    @OneToOne
    private Topic topic;

    private int room;

    @OneToOne
    private Teacher teacher2;

    @OneToOne
    private Topic topic2;
}
