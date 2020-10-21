package project.inz.scheduleChecker.repository;

import project.inz.scheduleChecker.model.Class;

public interface extendedClassRepository {
    public Class findClassByName(String name);
}
