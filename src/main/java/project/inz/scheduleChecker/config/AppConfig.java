package project.inz.scheduleChecker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import project.inz.scheduleChecker.converter.TeacherConverter;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver resolver= new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views");
//        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry){
        formatterRegistry.addConverter(getTeacherConverter());
    }

    @Bean
    public TeacherConverter getTeacherConverter() {
        return new TeacherConverter();
    }
}
