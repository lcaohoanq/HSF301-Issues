package fall24.hsf301.slot02.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fall24.hsf301.slot02.pojo.Student;

public class HStudentDAO implements IStudentDAO {

	private SessionFactory sessionFactory = null;
	private Configuration cf = null;
	
	public HStudentDAO(String persistanceName) {
		cf = new Configuration();
		cf = cf.configure(persistanceName);
		sessionFactory = cf.buildSessionFactory();
	}
	
	public void save(Student Student) {
		
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(Student);
			t.commit();
			System.out.println("Successfully saved");
		}catch (Exception e) {
			t.rollback();
			System.out.println("Error " + e.getMessage());
			// TODO: handle exception
		}finally {
//			sessionFactory.close();
			session.close();
		}
		
	}
	
	public List<Student> getStudents(){
		List<Student> Students = null;
		Session session = sessionFactory.openSession();
		try {
			Students = session.createQuery("from Student", Student.class).list();
		}catch(Exception e) {
			System.out.println("Erorr: " + e.getMessage());
		}finally {
//			sessionFactory.close();
		}
		return Students;
	}
	
	
	public void delete(Long StudentID) {
		
		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		try {
			t.begin();
			Student Student = (Student) session.get(Student.class, StudentID);
			session.delete(Student);
			t.commit();
			System.out.println("Successfully Delete");
		}catch (Exception e) {
			t.rollback();
			System.out.println("Error " + e.getMessage());
		}finally {
//			sessionFactory.close();
			session.close();
		}
		
	}
	
	public Student findById(Long StudentID) {
		
		Session session = sessionFactory.openSession();
		try {
			return (Student) session.get(Student.class, StudentID);
		}catch (Exception e) {
			throw e;
		}
		finally {
			session.close();
		}
	}
	
	public void update(Student Student) {
		
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(Student);
			t.commit();
			System.out.println("Update Success");
		}catch (Exception e) {
			t.rollback();
			System.out.println("Error " + e.getMessage());
		}
		finally {
//			sessionFactory.close();
			session.close();
		}
		
	}
	
}
