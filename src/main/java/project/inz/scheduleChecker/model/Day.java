package project.inz.scheduleChecker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "days")
@Getter @Setter @ToString
public class Day extends ParentEntity{

    private String name;
}
