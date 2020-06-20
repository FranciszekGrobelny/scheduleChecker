package project.inz.scheduleChecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ScheduleCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleCheckerApplication.class, args);
	}
}
