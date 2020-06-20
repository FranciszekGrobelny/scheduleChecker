package project.inz.scheduleChecker.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
@Getter @Setter @ToString
public class Room extends ParentEntity{

    @Column(nullable = false)
    private String number;
}
