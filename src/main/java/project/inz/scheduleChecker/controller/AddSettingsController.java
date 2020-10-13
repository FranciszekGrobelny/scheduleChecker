package project.inz.scheduleChecker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.inz.scheduleChecker.dto.SettingDTO;
import project.inz.scheduleChecker.service.SettingService;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Controller @Slf4j
public class AddSettingsController {

    private final SettingService settingService;

    public AddSettingsController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping("/addSettings")
    public String showSettingsView(){
        return "/add/settings.jsp";
    }

    @PostMapping("/addSettings")
    public String addSetting(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime  firstLessonStartTime,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime breakAfter45minLesson,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime breakAfter60minLesson,
                             @RequestParam int longBreakAfterLesson,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime longBreakFor45minLesson,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime longBreakFor60minLesson){

       SettingDTO settingDTO = new SettingDTO(
               longBreakAfterLesson,
               breakAfter45minLesson.plusHours(1),
               breakAfter60minLesson.plusHours(1),
               longBreakFor45minLesson.plusHours(1),
               longBreakFor60minLesson.plusHours(1),
               firstLessonStartTime.plusHours(1));
        try{
            settingService.save(settingDTO);
            return "/index.jsp";
        }catch (DataIntegrityViolationException e){
            System.out.println("Double teacher initials "+e.getMessage());
        }
        return "/add/duplicateEntryError.jsp";
    }
}
