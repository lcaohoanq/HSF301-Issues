package com.fe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fe.pojo.Student;

public class StudentDAO {

	private static EntityManager em;
	private static EntityManagerFactory emf;
	
	public StudentDAO(String persistenceName) {
		emf = Persistence.createEntityManagerFactory(persistenceName);
	}
	
	
	public void save(Student student) {
		
	}
	
	public List<Student> getStudents(){
		return null;
	}
	
	public void delete(Long studentId) {
		
	}
	
	public Student findById(Long studentId) {
		return null;
	}
	
	public void updateStudent(Student student) {
		
	}
	
	
	
	
	
}
