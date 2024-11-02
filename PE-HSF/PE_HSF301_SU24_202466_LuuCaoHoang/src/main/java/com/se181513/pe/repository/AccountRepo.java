package com.se181513.pe.repository;

import com.se181513.pe.dao.AccountDAO;
import com.se181513.pe.pojo.Account;

public class AccountRepo implements IAccountRepo{

	private AccountDAO accountDAO;
	
	public AccountRepo(String name) {
		accountDAO = new AccountDAO(name);
}
	
	@Override
	public Account findByUserName(String email) {
		// TODO Auto-generated method stub
		return accountDAO.findByUsername(email);

	}

}
