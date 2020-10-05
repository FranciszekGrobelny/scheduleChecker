package project.inz.scheduleChecker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.inz.scheduleChecker.model.TopicWithHoursQuantity;

public interface TopicWithHoursQuantityRepository extends JpaRepository<TopicWithHoursQuantity, Long> {
}
