package project.inz.scheduleChecker.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "classes")
@Getter @Setter @ToString
public class Class extends ParentEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String arabicName;

    @Column(nullable = false)
    private String mainTeacher;
    @OneToOne
    private Topic topic;

    @Column(nullable = false)
    private String topicHoours;
}
