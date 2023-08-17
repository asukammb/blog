package com.example.demo.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.AccountEntity;

public interface AccountDao extends JpaRepository<AccountEntity, Long> {
	
	AccountEntity save(AccountEntity accountEntity);
	
	AccountEntity findByEmail(String email);
	
	AccountEntity findByEmailAndPassword(String email,String password);

}
