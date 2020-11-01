package project.inz.scheduleChecker.model;

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

}
