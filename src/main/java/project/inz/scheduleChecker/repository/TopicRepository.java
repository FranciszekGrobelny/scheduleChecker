package project.inz.scheduleChecker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.inz.scheduleChecker.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
