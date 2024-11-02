package com.se181513.pe.main;

import com.se181513.pe.repository.AccountRepo;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = "hibernate.cfg.xml";
		AccountRepo accountRepo = new AccountRepo(filename);
	}

}
