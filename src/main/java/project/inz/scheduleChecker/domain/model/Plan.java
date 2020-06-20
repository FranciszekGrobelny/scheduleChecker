package project.inz.scheduleChecker.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plans")
@Getter @Setter @ToString
public class Plan extends ParentEntity{

    @OneToOne
    private Class clas;

    @OneToOne
    private Lesson lesson;

    @Column(nullable = false)
    private String day;

    @Column(nullable = false)
    private String lessonNumber;
}
