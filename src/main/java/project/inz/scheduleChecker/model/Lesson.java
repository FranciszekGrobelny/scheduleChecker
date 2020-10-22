package project.inz.scheduleChecker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Optional;

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

    @ManyToOne
    private Plan plan;

    public Lesson() {
    }

    public Lesson(boolean revalidationLesson,boolean connected,LocalTime startTime,LocalTime endTime,
                  Teacher teacher,String topic,int room,Plan plan) {
        this.revalidationLesson = revalidationLesson;
        this.connected = connected;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacher = teacher;
        this.topic = topic;
        this.room = room;
        this.plan = plan;
    }

    public Lesson(boolean revalidationLesson,boolean connected,LocalTime startTime,LocalTime endTime,
                  Teacher teacher,String topic,int room,Teacher teacher2,String topic2,Plan plan) {
        this.revalidationLesson = revalidationLesson;
        this.connected = connected;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacher = teacher;
        this.topic = topic;
        this.room = room;
        this.teacher2 = teacher2;
        this.topic2 = topic2;
        this.plan = plan;
    }

    public Lesson(boolean revalidationLesson, boolean connected, LocalTime start, LocalTime stop, Teacher teacherById, String religia, int room, Optional<Plan> plan) {
        super();
    }
}
