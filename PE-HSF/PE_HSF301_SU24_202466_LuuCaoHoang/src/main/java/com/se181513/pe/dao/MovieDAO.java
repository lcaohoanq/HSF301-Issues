package com.se181513.pe.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.se181513.pe.pojo.Movies;



public class MovieDAO {
	private SessionFactory sessionFactory;
	private Configuration configuration;
	
	public MovieDAO(String persistanceName) {
		configuration = new Configuration();
		configuration = configuration.configure(persistanceName);
		sessionFactory = configuration.buildSessionFactory();
	}
	public void save(Movies movie) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(movie);
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
	public List<Movies> getMovies() {
		List<Movies> movies = null;
		Session session = sessionFactory.openSession();
		try {
			movies = session.createQuery("from Movies", Movies.class).list();
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
			Movies movie = (Movies) session.get(Movies.class,movieId);
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
	
	public Movies findById(int movieId) {
		Session session = sessionFactory.openSession();
		try {
			return (Movies) session.get(Movies.class,movieId);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}
	
	
	public void update(Movies movie) {
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
	public List<Movies> searchByMName(String Mname){
		 List<Movies> list = new ArrayList<>();
		    Session session = sessionFactory.openSession();
		    try {
		    	  // Native SQL query to fetch movies based on movie name or director name
		        String sql = "SELECT m.* FROM Movies m "+ // Confirm column names match your DB schema
		                     "WHERE m.MovieName LIKE :Mname ";
		        
		        // Create the native query, specifying the result class
		        Query<Movies> query = session.createNativeQuery(sql, Movies.class);
		        
		        // Set query parameters for movie name and director name
		        query.setParameter("Mname", "%" + Mname + "%");
		        
		        // Execute query and get results
		        list = query.getResultList();
		    } catch (Exception e) {
		        System.out.println("Error: " + e.getMessage());
		    } finally {
		        session.close();
		    }
		    return list;
	}
	public List<Movies> searchByDName(String Dname){
		 List<Movies> list = new ArrayList<>();
		    Session session = sessionFactory.openSession();
		    try {
		    	  // Native SQL query to fetch movies based on movie name or director name
		    	 String sql = "SELECT m.* FROM Movies m " +
	                     "JOIN Directors d ON m.DirectorID = d.ID " +
	                     "WHERE d.DirectorName LIKE :Dname";
		        
		        // Create the native query, specifying the result class
		        Query<Movies> query = session.createNativeQuery(sql, Movies.class);
		        // Set query parameters for movie name and director name
		        query.setParameter("Dname", "%" + Dname + "%");
		        // Execute query and get results
		        list = query.getResultList();
		    } catch (Exception e) {
		        System.out.println("Error: " + e.getMessage());
		    } finally {
		        session.close();
		    }
		    return list;
	}
}
