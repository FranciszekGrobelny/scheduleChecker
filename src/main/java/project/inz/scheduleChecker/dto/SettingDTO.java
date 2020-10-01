package project.inz.scheduleChecker.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Getter @Setter @ToString
public class SettingDTO {

    @NotNull
    private LocalTime breakAfterLesson;
}
