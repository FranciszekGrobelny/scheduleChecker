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

    private boolean revalidationLesson;
    
    private boolean connected;

    private LocalTime startTime;

    private LocalTime endTime;

    @OneToOne
    private Teacher teacher;

    private String topic;

    private int room;

    @OneToOne
    private Teacher teacher2;

    private String topic2;
}
