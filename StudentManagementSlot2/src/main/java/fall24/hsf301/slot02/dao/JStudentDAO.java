package fall24.hsf301.slot02.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fall24.hsf301.slot02.pojo.Student;


public class JStudentDAO implements IStudentDAO{

	private static EntityManager em;
	private static EntityManagerFactory emf;
	
	public JStudentDAO(String persistenceName) {
		emf = Persistence.createEntityManagerFactory(persistenceName);
	}
	
	
	public void save(Student Student) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(Student);
			em.getTransaction().commit();
		}catch(Exception ex) {
			em.getTransaction().rollback();
			System.out.println("Error: " + ex.getMessage());
		}
	}
	
	public List<Student> getStudents(){
		List<Student> Students = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Students = em.createQuery("from Student").getResultList();
		}catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}finally {
			em.close();
		}
		return Students;
	}
	
	public void delete(Long StudentId) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Student Student = em.find(Student.class, StudentId);
			em.remove(Student);
			em.getTransaction().commit();			
		}catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}finally {
			em.close();
		}
	}
	
	public Student findById(Long StudentId) {
		Student Student = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Student = em.find(Student.class, StudentId);
		}catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}finally {
			em.close();
		}
		return Student;
	}
	
	public void update(Student Student) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			Student s = em.find(Student.class, Student.getId());
			if(s != null) {
				s.setFirstName(Student.getFirstName());
				s.setLastName(Student.getLastName());
				s.setMark(Student.getMark());
				em.merge(s);
				em.getTransaction().commit();
			}
			
		}catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}finally {
			em.close();
		}
	}
	
}
