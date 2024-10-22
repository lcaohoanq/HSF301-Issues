package pe.hsf301.fall24.dao.movie;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import pe.hsf301.fall24.pojo.Movie;

public class MovieDAO implements IMovieDAO {

	private SessionFactory sessionFactory = null;
	private Configuration cf = null;

	public MovieDAO(String persistanceName) {
		cf = new Configuration();
		cf = cf.configure(persistanceName);
		sessionFactory = cf.buildSessionFactory();
	}

	@Override
	public void save(Movie movie) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(movie);
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
	public List<Movie> findAll() {
		List<Movie> Movies = null;
		Session session = sessionFactory.openSession();
		try {
			Movies = session.createQuery("from Movie", Movie.class).list();
		} catch (Exception e) {
			System.out.println("Erorr: " + e.getMessage());
		} finally {
//			sessionFactory.close();
		}
		return Movies;
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		try {
			t.begin();
			Movie account = (Movie) session.get(Movie.class, id);
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
	public Movie findById(Integer id) {
		Session session = sessionFactory.openSession();
		try {
			return (Movie) session.get(Movie.class, id);
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Movie movie) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(movie);
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
