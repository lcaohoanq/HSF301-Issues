package fall24.hsf301.slot02.service;

import java.util.List;

import fall24.hsf301.slot02.pojo.Student;

public interface IStudentService {
	void save(Student Student);	
	List<Student> getStudents();	
	void delete(Long StudentId);	
	Student findById(Long StudentId);
	void update(Student Student);
	Student readInformation();
}