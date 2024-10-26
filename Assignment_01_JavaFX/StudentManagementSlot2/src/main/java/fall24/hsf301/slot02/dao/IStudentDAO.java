package fall24.hsf301.slot02.dao;

import java.util.List;

import fall24.hsf301.slot02.pojo.Student;

public interface IStudentDAO {

	void save(Student Student);	
	List<Student> getStudents();	
	void delete(Long StudentId);	
	Student findById(Long StudentId);
	void update(Student Student);	
	
}
