package project.inz.scheduleChecker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "topics_withs_hours_quantity")
@Getter @Setter @ToString
public class TopicWithHoursQuantity extends ParentEntity{

    @OneToOne
    private Topic topic;

    private int hoursQuantity;
}
