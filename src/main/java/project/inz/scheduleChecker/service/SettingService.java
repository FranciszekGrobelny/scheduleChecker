package project.inz.scheduleChecker.service;

import org.springframework.stereotype.Service;
import project.inz.scheduleChecker.model.Setting;
import project.inz.scheduleChecker.repository.SettingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettingService {

    private final SettingRepository settingRepository;

    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    public void save(Setting setting) {

        settingRepository.save(setting);
    }

    public List<Setting> findAll() {
        return settingRepository.findAll();
    }

    public List<String> findFirstToString() {
        List<String> list = new ArrayList<>();
        Setting setting = settingRepository.findFirst();
        list.add(Integer.toString(setting.getLongBreakAfterLesson()));
        list.add(setting.getFirstLessonStartTime().toString());
        list.add(setting.getShortBreak().toString());
        list.add(setting.getLongBreak().toString());

        return list;
    }

    public void update(Setting setting) {
        this.deleteAll();
        settingRepository.save(setting);
    }

    public void delete(Long id) {
        settingRepository.deleteById(id);
    }

    public void deleteAll() {
        settingRepository.deleteAll();
    }

}
