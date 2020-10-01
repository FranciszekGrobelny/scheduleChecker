package project.inz.scheduleChecker.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class TeacherDTO {

    @NotNull
    private String initialLetters;

    private String hours;

    private String isSpecialist;

}
