package fall24.hsf301.slot02.service;

import java.util.List;
import java.util.Scanner;

import fall24.hsf301.slot02.pojo.Student;
import fall24.hsf301.slot02.repository.IStudentRepository;
import fall24.hsf301.slot02.repository.StudentRepo;

public class StudentService implements IStudentService{

	private IStudentRepository studentRepository;
	
	public StudentService(String jpaName) {
		studentRepository = new StudentRepo(jpaName);
	}
	
	@Override
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	public List<Student> getStudents() {
		return studentRepository.getStudents();
	}

	@Override
	public void delete(Long studentId) {
		studentRepository.delete(studentId);
	}

	@Override
	public Student findById(Long studentId) {
		return studentRepository.findById(studentId);
	}

	@Override
	public void update(Student student) {
		studentRepository.update(student);
	}

	@Override
	public Student readInformation() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter student first name: ");
		String firstName = sc.nextLine();
		System.out.println("Enter student last name: ");
		String lastName = sc.nextLine();
		System.out.println("Enter student mark: ");
		int mark = Integer.parseInt(sc.nextLine());
		return new Student(firstName, lastName, mark);
	}

}
