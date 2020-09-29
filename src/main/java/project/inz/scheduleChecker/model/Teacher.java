package project.inz.scheduleChecker.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teachers")
@Getter @Setter @ToString
public class Teacher extends ParentEntity {

    @Column(nullable = false)
    private String letters;

    @Column(nullable = false)
    private String hours;

    private String isSpecialist;


}
