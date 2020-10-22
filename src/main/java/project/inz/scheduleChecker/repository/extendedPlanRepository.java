package project.inz.scheduleChecker.repository;

import project.inz.scheduleChecker.model.Plan;

public interface extendedPlanRepository {
    public Plan findPlanById(Long id);
}
