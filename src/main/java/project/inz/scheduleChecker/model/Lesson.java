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

    private int lessonNumber;

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

    @OneToOne
    private Day day;

    public Lesson() {
    }

    public Lesson(boolean revalidationLesson,int lessonNumber,LocalTime startTime,LocalTime endTime,
                  Teacher teacher,String topic,int room,Plan plan, Day day) {
        this.revalidationLesson = revalidationLesson;
        this.lessonNumber = lessonNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacher = teacher;
        this.topic = topic;
        this.room = room;
        this.plan = plan;
        this.day = day;
    }

    public Lesson(boolean revalidationLesson,boolean connected,int lessonNumber,LocalTime startTime,LocalTime endTime,
                  Teacher teacher,String topic,int room,Teacher teacher2,String topic2,Plan plan) {
        this.revalidationLesson = revalidationLesson;
        this.connected = connected;
        this.lessonNumber = lessonNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacher = teacher;
        this.topic = topic;
        this.room = room;
        this.teacher2 = teacher2;
        this.topic2 = topic2;
        this.plan = plan;
    }

    public boolean isRevalidationLesson() {
        return revalidationLesson;
    }

}
