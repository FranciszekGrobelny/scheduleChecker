package project.inz.scheduleChecker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.inz.scheduleChecker.model.Setting;
import project.inz.scheduleChecker.service.SettingService;

import java.time.LocalTime;

@Controller
@Slf4j
public class AddSettingsController {

    private final SettingService settingService;

    public AddSettingsController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping("/addSettings")
    public String showSettingsView(Model model) {
        log.warn(settingService.findAll().toString());
        if (settingService.findAll().size() > 0) {
            model.addAttribute("settings", settingService.findFirstToString());
        }

        return "/add/settings.jsp";
    }

    @PostMapping("/addSettings")
    public String addSetting(@RequestParam int longBreakAfterLesson,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime breakAfter45minLesson,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime breakAfter60minLesson,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime longBreakFor45minLesson,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime longBreakFor60minLesson,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime firstLessonStartTime) {

        Setting setting = new Setting(
                longBreakAfterLesson,
                breakAfter45minLesson,
                breakAfter60minLesson,
                longBreakFor45minLesson,
                longBreakFor60minLesson,
                firstLessonStartTime);
        try {
            if (settingService.findAll().size() > 0) {
                settingService.update(setting);
            } else {
                settingService.save(setting);
            }
            log.warn(settingService.findAll().toString());
            return "/index.jsp";
        } catch (DataIntegrityViolationException e) {
            System.out.println("Double teacher initials " + e.getMessage());
        }
        return "/add/duplicateEntryError.jsp";
    }
}
