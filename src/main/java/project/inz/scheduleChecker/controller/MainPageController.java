package project.inz.scheduleChecker.web.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.inz.scheduleChecker.domain.model.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainPageController {

    private final JdbcTemplate jdbcTemplate;

    MainPageController(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/showTeachers")
    @ResponseBody
    public String mainPage(){

        List<Teacher> list = findAll();
        return list.toString();
    }

    public List<Teacher> findAll() {

        String sql = "SELECT * FROM teachers"; //wczytywanie powinno byc po kolumnach nie po gwiazdkach, aby wartosci sie nie mieszały między wierszami

        List<Teacher> teachers = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map row : rows) {
            Teacher obj = new Teacher();

            obj.setLetters((String) row.get("letters"));
            obj.setHours((String) row.get("hours"));
            obj.setIsSpecialist((String) row.get("is_specialist"));

            teachers.add(obj);
        }

        return teachers;
    }
}
