package pe.hsf301.fall24.repository.account;

import java.util.List;

import pe.hsf301.fall24.dao.account.AccountDAO;
import pe.hsf301.fall24.dao.account.IAccountDAO;
import pe.hsf301.fall24.pojo.Account;

public class AccountRepo implements IAccountRepository {
	
	private IAccountDAO accountDAO;
	
	public AccountRepo(String jpaName) {
		accountDAO = new AccountDAO(jpaName);
	}

	@Override
	public void save(Account account) {
		accountDAO.save(account);
	}

	@Override
	public List<Account> findAll() {
		return accountDAO.findAll();
	}

	@Override
	public void delete(Integer id) {
		accountDAO.delete(id);
	}

	@Override
	public Account findById(Integer id) {
		return accountDAO.findById(id);
	}

	@Override
	public void update(Account account) {
		accountDAO.update(account);
	}

	@Override
	public Account login(String email, String password) {
		return accountDAO.login(email, password);
	}

}
