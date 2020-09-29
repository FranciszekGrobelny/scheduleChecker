package project.inz.scheduleChecker.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lessons")
@Getter @Setter @ToString
public class Lesson extends ParentEntity{

    @Column(nullable = false)
    private String type;
    
    private String connected;

    @OneToOne
    private Teacher teacher;

    @OneToOne
    private Topic topic;

    @OneToOne
    private Room room;

    @OneToOne
    private Teacher teacher2;

    @OneToOne
    private Topic topic2;

    @OneToOne
    private Room room2;
}
