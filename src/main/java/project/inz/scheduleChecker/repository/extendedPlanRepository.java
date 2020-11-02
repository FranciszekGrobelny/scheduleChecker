package project.inz.scheduleChecker.repository;

import project.inz.scheduleChecker.model.Plan;

import java.util.Optional;

public interface extendedPlanRepository {

    Plan findPlanById(Long id);
    Plan findPlanByClass(String className);
    Optional<Plan> findPlanByClassOpt(String className);
}
