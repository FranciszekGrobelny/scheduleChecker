package project.inz.scheduleChecker.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "topics")
@Getter @Setter @ToString
public class Topic extends ParentEntity {

    @Column(nullable = false)
    private String name;
}
