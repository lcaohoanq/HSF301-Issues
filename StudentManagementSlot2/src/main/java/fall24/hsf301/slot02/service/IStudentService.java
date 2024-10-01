package fall24.hsf301.slot02.service;

import java.util.List;

import fall24.hsf301.slot02.pojo.Student;

public interface IStudentService {
	void save(Student student);	
	List<Student> getStudents();	
	void delete(Long studentId);	
	Student findById(Long studentId);
	void update(Student student);
	Student readInformation();
}
