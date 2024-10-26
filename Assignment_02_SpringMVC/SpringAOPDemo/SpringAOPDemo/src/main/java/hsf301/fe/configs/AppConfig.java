package hsf301.fe.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import hsf301.fe.aspects.LoggingAspect;
import hsf301.fe.services.IStudentService;
import hsf301.fe.services.StudentService;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
	
	@Bean
    public IStudentService myService() {
        return new StudentService();
    }

    @Bean
	public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
