package com.atmmachine.repository;

import com.atmmachine.model.Account;
import com.atmmachine.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	

	List<Transaction> findByAccountAndTransactionTimeBetween(Account account, LocalDateTime atStartOfDay,
			LocalDateTime atTime);
}
