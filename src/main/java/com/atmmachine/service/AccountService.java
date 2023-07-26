package com.atmmachine.service;

import com.atmmachine.dto.AccountDTO;
import com.atmmachine.exception.AccountNotFoundException;
import com.atmmachine.exception.InsufficientBalanceException;
import com.atmmachine.model.Account;
//import com.atmmachine.model.ChangePINRequest;
import com.atmmachine.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {
    
	private final AccountRepository accountRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public AccountDTO getAccountByAccountNumber(String accountNumber) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		if (account != null) {
			return mapAccountToAccountDTO(account);
		} else {
			return null;
		}
	}

	public AccountDTO createAccount(AccountDTO accountDTO) {
		Account account = new Account();
		account.setId(accountDTO.getId());
		account.setAccountNumber(accountDTO.getAccountNumber());
		account.setBalance(accountDTO.getBalance());
		account.setPin(accountDTO.getPin());
		
	

		account = accountRepository.save(account);
		
		return mapAccountToAccountDTO(account);
	}

	public AccountDTO depositToAccount(String accountNumber, BigDecimal amount) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		if (account != null && amount != null && amount.compareTo(BigDecimal.ZERO) > 0) {
			account.deposit(amount);
			account = accountRepository.save(account);
			return mapAccountToAccountDTO(account);
		} else {
			return null;
		}
	}

	public AccountDTO withdrawFromAccount(String accountNumber, BigDecimal amount) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		if (account != null && amount != null && amount.compareTo(BigDecimal.ZERO) > 0
				&& account.getBalance().compareTo(amount) >= 0) {
			account.withdraw(amount);
			account = accountRepository.save(account);
			return mapAccountToAccountDTO(account);
		} else {
			return null;
		}
	}

	public boolean deleteAccount(String accountNumber) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		if (account != null) {
			accountRepository.delete(account);
			return true;
		} else {
			return false;
		}
	}

	private AccountDTO mapAccountToAccountDTO(Account account) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(account.getId());
		accountDTO.setAccountNumber(account.getAccountNumber());
		accountDTO.setBalance(account.getBalance());
		accountDTO.setPin(account.getPin());
		return accountDTO;
	}


    public boolean changePIN(String accountNumber, String currentPIN, String newPIN) {
        // First, authenticate the user (you can implement JWT or other authentication mechanisms here).

        // Then, validate the current PIN against the one stored in the database.
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null && account.getPin().equals(currentPIN)) {
            // If the current PIN is valid, update the PIN in the database.
            account.setPin(newPIN);
            // Call the method to save the updated account in the database.
            accountRepository.save(account);
            return true;
        }
        return false;
    }
    @Transactional
    public void transferFunds(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);
        if (fromAccount.getAccountNumber() == null || toAccount == null) {
            throw new AccountNotFoundException("Account not found.");
        }

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for transfer.");
        }

        // Perform the fund transfer
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
}
   
}
