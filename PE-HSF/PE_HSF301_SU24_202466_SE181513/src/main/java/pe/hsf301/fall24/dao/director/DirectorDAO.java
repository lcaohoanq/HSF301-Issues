package pe.hsf301.fall24.dao.director;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import pe.hsf301.fall24.pojo.Director;

public class DirectorDAO implements IDirectorDAO{

	private SessionFactory sessionFactory = null;
	private Configuration cf = null;

	public DirectorDAO(String persistanceName) {
		cf = new Configuration();
		cf = cf.configure(persistanceName);
		sessionFactory = cf.buildSessionFactory();
	}

	@Override
	public void save(Director director) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(director);
			t.commit();
			System.out.println("Successfully saved");
		} catch (Exception e) {
			t.rollback();
			System.out.println("Error " + e.getMessage());
			// TODO: handle exception
		} finally {
//			sessionFactory.close();
			session.close();
		}
	}

	@Override
	public List<Director> findAll() {
		List<Director> Directors = null;
		Session session = sessionFactory.openSession();
		try {
			Directors = session.createQuery("from Director", Director.class).list();
		} catch (Exception e) {
			System.out.println("Erorr: " + e.getMessage());
		} finally {
//			sessionFactory.close();
		}
		return Directors;
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		try {
			t.begin();
			Director account = (Director) session.get(Director.class, id);
			session.delete(account);
			t.commit();
			System.out.println("Successfully Delete");
		} catch (Exception e) {
			t.rollback();
			System.out.println("Error " + e.getMessage());
		} finally {
//			sessionFactory.close();
			session.close();
		}

	}

	@Override
	public Director findById(Integer id) {
		Session session = sessionFactory.openSession();
		try {
			return (Director) session.get(Director.class, id);
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Director director) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(director);
			t.commit();
			System.out.println("Update Success");
		} catch (Exception e) {
			t.rollback();
			System.out.println("Error " + e.getMessage());
		} finally {
//			sessionFactory.close();
			session.close();
		}

	}
}
