package fall24.hsf301.slot02.service;

import java.util.List;
import java.util.Scanner;

import fall24.hsf301.slot02.pojo.Student;
import fall24.hsf301.slot02.repository.IStudentRepository;
import fall24.hsf301.slot02.repository.StudentRepo;

public class StudentService implements IStudentService{

	private IStudentRepository StudentRepository;
	
	public StudentService(String jpaName, int type) {
		StudentRepository = new StudentRepo(jpaName, type);
	}
	
	@Override
	public void save(Student Student) {
		StudentRepository.save(Student);
	}

	@Override
	public List<Student> getStudents() {
		return StudentRepository.getStudents();
	}

	@Override
	public void delete(Long StudentId) {
		StudentRepository.delete(StudentId);
	}

	@Override
	public Student findById(Long StudentId) {
		return StudentRepository.findById(StudentId);
	}

	@Override
	public void update(Student Student) {
		StudentRepository.update(Student);
	}

	@Override
	public Student readInformation() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Student first name: ");
		String firstName = sc.nextLine();
		System.out.println("Enter Student last name: ");
		String lastName = sc.nextLine();
		System.out.println("Enter Student mark: ");
		int mark = Integer.parseInt(sc.nextLine());
		return new Student(firstName, lastName, mark);
	}

}
