package project.inz.scheduleChecker.service;

import org.springframework.stereotype.Service;
import project.inz.scheduleChecker.dto.SettingDTO;
import project.inz.scheduleChecker.model.Setting;
import project.inz.scheduleChecker.repository.SettingRepository;

import java.util.List;

@Service
public class SettingService {

    private SettingRepository settingRepository;

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

    public void update(SettingDTO settingDTO){
        Setting setting  = createClassFromClassDTO(settingDTO);
        settingRepository.save(setting);
    }

    public void delete(Long id){
        settingRepository.deleteById(id);
    }

    private Setting createClassFromClassDTO(SettingDTO settingDTO) {
        Setting setting = new Setting();
        setting.setBreakAfterLesson(settingDTO.getBreakAfterLesson());

        return setting;
    }
}
