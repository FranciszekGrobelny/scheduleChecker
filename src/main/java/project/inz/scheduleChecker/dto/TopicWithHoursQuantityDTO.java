package project.inz.scheduleChecker.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import project.inz.scheduleChecker.model.Topic;

@Getter @Setter @ToString
public class TopicWithHoursQuantityDTO {

    @NotNull
    private Topic topic;

    @NotNull
    private int hoursQuantity;
}
