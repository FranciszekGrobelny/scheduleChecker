package project.inz.scheduleChecker.model;

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

    private String initialLetters;

    private String hours;

    private boolean isSpecialist;

    public boolean getIsSpecialist() {
        return isSpecialist;
    }

    public void setIsSpecialist(boolean specialist) {
        isSpecialist = specialist;
    }
}
