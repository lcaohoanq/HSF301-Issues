package fall24.hsf301.slot02.repository;

import java.util.List;

import fall24.hsf301.slot02.dao.StudentDAO;
import fall24.hsf301.slot02.pojo.Student;

public class StudentRepo implements IStudentRepository {

	private StudentDAO studentDAO;
	
	public StudentRepo(String jpaName) {
		studentDAO = new StudentDAO(jpaName);
	}

	@Override
	public void save(Student student) {
		studentDAO.save(student);
	}

	@Override
	public List<Student> getStudents() {
		return studentDAO.getStudents();
	}

	@Override
	public void delete(Long studentId) {
		studentDAO.delete(studentId);
	}

	@Override
	public Student findById(Long studentId) {
		return studentDAO.findById(studentId);
	}

	@Override
	public void update(Student student) {
		studentDAO.update(student);
	}

}
