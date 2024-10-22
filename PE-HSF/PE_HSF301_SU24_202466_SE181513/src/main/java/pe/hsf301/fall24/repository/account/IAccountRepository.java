package pe.hsf301.fall24.repository.account;

import java.util.List;

import pe.hsf301.fall24.pojo.Account;

public interface IAccountRepository {

	void save(Account account);	
	List<Account> findAll();	
	void delete(Integer id);	
	Account findById(Integer id);
	void update(Account account);
	Account login(String email, String password);
}
