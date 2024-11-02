package com.se181513.pe.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.se181513.pe.pojo.Directors;


public class DirectorDAO {
	private SessionFactory sessionFactory;
	private Configuration configuration;
	
	public DirectorDAO(String persistanceName) {
		configuration = new Configuration();
		configuration = configuration.configure(persistanceName);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	public void save(Directors director) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(director);
			t.commit();
			System.out.println("Successfully saved");
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			System.out.println("Error" + e.getMessage());
		} finally {
		//	sessionFactory.close();
			session.close();
		}
	}
	
	public List<Directors> findAll() {
		List<Directors> Directors = null;
		Session session = sessionFactory.openSession();
		try {
			Directors = session.createQuery("from Directors", Directors.class).list();
		} catch (Exception e) {
			System.out.println("Erorr: " + e.getMessage());
		} finally {
//			sessionFactory.close();
		}
		return Directors;
	}
	
	public List<Directors> getMovies() {
		List<Directors> movies = null;
		Session session = sessionFactory.openSession();
		try {
			movies = session.createQuery("from Movies", Directors.class).list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error" + e.getMessage());
		} finally {
			//sessionFactory.close();
			session.close();
		}
		return movies;
	}
	public void delete(Integer movieId) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Directors movie = (Directors) session.get(Directors.class,movieId);
			session.delete(movie);
			t.commit();
			System.out.println("Delete saved");
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			throw e;
		} finally {
//			sessionFactory.close();
			session.close();
		}
	}
	
	public Directors findById(int movieId) {
		Session session = sessionFactory.openSession();
		try {
			return (Directors) session.get(Directors.class,movieId);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}
	
	
	public void update(Directors movie) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(movie);
			t.commit();
			System.out.println("Update successfully");
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			System.out.println("Error "+e.getMessage());
		} finally {
			//sessionFactory.close();
			session.close();
		}
	}
}
