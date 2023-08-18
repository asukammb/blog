package blog.ex.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.ex.model.dao.AccountDao;
import blog.ex.model.entity.AccountEntity;

@Service
public class AccountService {
	@Autowired
	private AccountDao accountDao;
	
	
	public boolean createAccount(String accountName,String email,String password) {
		LocalDateTime registerDate = LocalDateTime.now();
		
		AccountEntity accountEntity = accountDao.findByEmail(email);
		
		if(accountEntity==null) {
			accountDao.save(new AccountEntity(accountName,email,password,registerDate));
			return true;
		}else {
			return false;
		}
	}
	
	public AccountEntity loginAccount(String email,String password) {
		AccountEntity accountEntity = accountDao.findByEmailAndPassword(email,password);
		if(accountEntity == null) {
			return null;
		}else {
			return accountEntity;
		}
	}

}