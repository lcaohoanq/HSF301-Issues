package com.se181513.pe.dao;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.se181513.pe.pojo.Account;


public class AccountDAO {
	private SessionFactory sessionFactory;
	private Configuration configuration;

	public AccountDAO(String persistanceName) {
		configuration = new Configuration();
		configuration = configuration.configure(persistanceName);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	public Account findByUsername(String email) {
		Account account = null;
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			account = session.find(Account.class, email);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error" + e.getMessage());
		} finally {
			session.close();
		}
		return account;
	}
}
