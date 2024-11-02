package com.se181513.pe.repository;

import com.se181513.pe.pojo.Account;

public interface IAccountRepo {
	public Account findByUserName(String email);
}
