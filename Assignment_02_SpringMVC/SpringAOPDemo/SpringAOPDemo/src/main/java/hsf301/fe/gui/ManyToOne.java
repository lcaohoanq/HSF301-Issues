package hsf301.fe.gui;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hsf301.fe.configs.AppConfig;
import hsf301.fe.pojos.Student;
import hsf301.fe.services.IStudentService;

public class ManyToOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		IStudentService myService = context.getBean(IStudentService.class);
		Student st = new Student("Lam", "Nguyen", 8);
		myService.save(st);
	}

}
