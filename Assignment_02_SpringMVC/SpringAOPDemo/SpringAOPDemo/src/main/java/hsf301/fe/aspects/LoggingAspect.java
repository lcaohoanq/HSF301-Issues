package hsf301.fe.aspects;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


//lamdeptrai
@Aspect
public class LoggingAspect {
	private static final Logger logger = LogManager.getLogger(LoggingAspect.class);
	public LoggingAspect() {
       // PropertyConfigurator.configure("src/main/resources/log4j.properties");
	}
	@Before("execution(* hsf301.fe.services.StudentService.save(..))")
	public void logBefore() {
		logger.debug("LoggingAspect: Before method Save Student()");
		System.out.println("LoggingAspect: Before method Save Student()");
	}
	
	@After("execution(* hsf301.fe.services.StudentService.save(..))")
	public void logAfter() {
		logger.debug("LoggingAspect: After method Save Student()");
		System.out.println("LoggingAspect: After method  Save Student()");
	}
}
