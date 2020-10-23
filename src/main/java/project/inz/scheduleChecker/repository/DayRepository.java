package project.inz.scheduleChecker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.inz.scheduleChecker.model.Day;

public interface DayRepository extends JpaRepository<Day, Long>, extendedDayRepository {

}
