package project.inz.scheduleChecker.repository;

import project.inz.scheduleChecker.model.Class;

import java.util.List;

public interface extendedClassRepository {
    Class findClassByName(String name);

    List<Class> findAllWithTopics();
}
