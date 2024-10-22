package pe.hsf301.fall24.dao.account;

import java.util.List;

import pe.hsf301.fall24.pojo.Account;


public interface IAccountDAO {
	void save(Account account);	
	List<Account> findAll();	
	void delete(Integer id);	
	Account findById(Integer id);
	List<Account> findByPassword(String password);
	void update(Account account);
	Account login(String email, String password);
}
