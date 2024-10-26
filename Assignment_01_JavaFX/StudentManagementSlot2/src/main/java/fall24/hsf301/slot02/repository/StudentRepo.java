package fall24.hsf301.slot02.repository;

import java.util.List;

import fall24.hsf301.slot02.dao.HStudentDAO;
import fall24.hsf301.slot02.dao.IStudentDAO;
import fall24.hsf301.slot02.dao.JStudentDAO;
import fall24.hsf301.slot02.pojo.Student;

public class StudentRepo implements IStudentRepository {

	private IStudentDAO StudentDAO;
	
	public StudentRepo(String jpaName, int type) {
		if(type == 1) {
			StudentDAO = new JStudentDAO(jpaName);
		}else {
			StudentDAO = new HStudentDAO(jpaName);
		}
	}

	@Override
	public void save(Student Student) {
		StudentDAO.save(Student);
	}

	@Override
	public List<Student> getStudents() {
		return StudentDAO.getStudents();
	}

	@Override
	public void delete(Long StudentId) {
		StudentDAO.delete(StudentId);
	}

	@Override
	public Student findById(Long StudentId) {
		return StudentDAO.findById(StudentId);
	}

	@Override
	public void update(Student Student) {
		StudentDAO.update(Student);
	}

}
