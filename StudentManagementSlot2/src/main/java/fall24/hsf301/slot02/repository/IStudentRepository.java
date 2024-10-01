package fall24.hsf301.slot02.repository;

import java.util.List;

import fall24.hsf301.slot02.pojo.Student;

public interface IStudentRepository {

	void save(Student student);	
	List<Student> getStudents();	
	void delete(Long studentId);	
	Student findById(Long studentId);
	void update(Student student);	
}
