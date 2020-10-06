package project.inz.scheduleChecker.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter @Setter @ToString
public class TeacherDTO {

    @NotNull
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
