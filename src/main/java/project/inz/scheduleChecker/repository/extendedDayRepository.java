package project.inz.scheduleChecker.repository;

import project.inz.scheduleChecker.model.Day;

public interface extendedDayRepository {
    Day findByIdNoOptional(Long id);
}
