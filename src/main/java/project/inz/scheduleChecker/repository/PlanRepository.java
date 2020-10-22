package project.inz.scheduleChecker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.inz.scheduleChecker.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long>,extendedPlanRepository {

}
