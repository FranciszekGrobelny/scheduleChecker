package project.inz.scheduleChecker.service;

import org.springframework.stereotype.Service;
import project.inz.scheduleChecker.dto.SettingDTO;
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

    public void save(SettingDTO settingDTO){
        Setting setting  = createClassFromClassDTO(settingDTO);
        settingRepository.save(setting);
    }

    public List<Setting> findAll(){
        return settingRepository.findAll();
    }

    public List<String> findFirstToString(){
        List<String> list = new ArrayList<>();
        Setting setting = settingRepository.findFirst();
        list.add(Integer.toString(setting.getLongBreakAfterLesson()));
        list.add(setting.getFirstLessonStartTime().toString());
        list.add(setting.getBreakAfter45minLesson().toString());
        list.add(setting.getBreakAfter60minLesson().toString());
        list.add(setting.getLongBreakFor45minLesson().toString());
        list.add(setting.getLongBreakFor60minLesson().toString());

        return list;
    }

    public void update(SettingDTO settingDTO){
        this.deleteAll();
        Setting setting  = createClassFromClassDTO(settingDTO);
        settingRepository.save(setting);
    }

    public void delete(Long id){
        settingRepository.deleteById(id);
    }

    public void deleteAll(){settingRepository.deleteAll();}

    private Setting createClassFromClassDTO(SettingDTO settingDTO) {
        Setting setting = new Setting();
        setting.setLongBreakAfterLesson(settingDTO.getLongBreakAfterLesson());
        setting.setBreakAfter45minLesson(settingDTO.getBreakAfter45minLesson());
        setting.setBreakAfter60minLesson(settingDTO.getBreakAfter60minLesson());
        setting.setFirstLessonStartTime(settingDTO.getFirstLessonStartTime());
        setting.setLongBreakFor45minLesson(settingDTO.getLongBreakFor45minLesson());
        setting.setLongBreakFor60minLesson(settingDTO.getLongBreakFor60minLesson());

        return setting;
    }
}
