package com.atmmachine.repository;

import com.atmmachine.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByAccountNumber(String accountNumber);

	Account findByUserIdAndAccountNumberNot(Long userId, String accountNumber);
	
}
