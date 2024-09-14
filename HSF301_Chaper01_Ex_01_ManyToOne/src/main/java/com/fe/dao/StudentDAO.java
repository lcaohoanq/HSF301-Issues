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
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(student);
			em.getTransaction().commit();
		}catch(Exception ex) {
			em.getTransaction().rollback();
			System.out.println("Error: " + ex.getMessage());
		}
	}
	
	public List<Student> getStudents(){
		List<Student> students = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			students = em.createQuery("from Student").getResultList();
		}catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}finally {
			em.close();
		}
		return students;
	}
	
	public void delete(Long studentId) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Student student = em.find(Student.class, studentId);
			em.remove(student);
			em.getTransaction().commit();			
		}catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}finally {
			em.close();
		}
	}
	
	public Student findById(Long studentId) {
		Student student = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			student = em.find(Student.class, studentId);
		}catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}finally {
			em.close();
		}
		return student;
	}
	
	public void updateStudent(Student student) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			Student s = em.find(Student.class, student.getId());
			if(s != null) {
				s.setFirstName(student.getFirstName());
				s.setLastName(student.getLastName());
				s.setMarks(student.getMarks());
				em.getTransaction().commit();
			}
			
		}catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}finally {
			em.close();
		}
	}
	
}
