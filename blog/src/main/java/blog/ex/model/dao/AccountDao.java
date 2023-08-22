package blog.ex.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.ex.model.entity.AccountEntity;

@Repository
public interface AccountDao extends JpaRepository<AccountEntity, Long> {
	
	AccountEntity save(AccountEntity accountEntity);
	
	AccountEntity findByEmail(String email);
	
	AccountEntity findByEmailAndPassword(String email,String password);
	
	List<AccountEntity> findAll();
}
