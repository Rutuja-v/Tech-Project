package com.atmmachine.service;

import com.atmmachine.dto.ContactlessTransactionRequestDTO;
import com.atmmachine.dto.TransactionRequestDTO;
import com.atmmachine.exception.AccountNotFoundException;
import com.atmmachine.exception.InsufficientBalanceException;
import com.atmmachine.exception.InvalidBillAmountException;
import com.atmmachine.exception.TransactionNotFoundException;
import com.atmmachine.model.Account;
import com.atmmachine.model.Transaction;
import com.atmmachine.repository.AccountRepository;
import com.atmmachine.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;

@Service
public class TransactionService {

	private final TransactionRepository transactionRepository;
	private final AccountRepository accountRepository;

	@Autowired
	public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
		this.transactionRepository = transactionRepository;
		this.accountRepository = accountRepository;
	}

	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

//	   public List<TransactionDTO> getAllTransactions() {
//	        List<Transaction> transactions = transactionRepository.findAll();
//	        List<TransactionDTO> transactionDTOs = new ArrayList<>();
//
//	        for (Transaction transaction : transactions) {
//	            TransactionDTO transactionDTO = new TransactionDTO();
//	            // Populate required fields from the transaction
//	            transactionDTO.setId(transaction.getId());
//	            // Populate other required fields from the transaction
//
//	            // Populate required fields from the associated account
//	            transactionDTO.setAccountNumber(transaction.getAccount().getAccountNumber());
//	            // Populate other required fields from the account
//
//	            transactionDTOs.add(transactionDTO);
//	        }
//
//	        return transactionDTOs;
//	    }
	public Transaction createTransaction(TransactionRequestDTO transactionRequest) {
		Account account = accountRepository.findByAccountNumber(transactionRequest.getAccountNumber());

		if (account == null) {
			// Account not found, handle the scenario accordingly (e.g., throw an exception
			// or return an error response).
			throw new AccountNotFoundException("Account not found for the given account number");
		}

		BigDecimal amount = transactionRequest.getAmount();

		if (account.getBalance().compareTo(amount) >= 0) {
			// Sufficient balance, create transaction
			Transaction transaction = new Transaction();
			transaction.setAccount(account);
			transaction.setAmount(amount);
			transaction.setTransactionTime(LocalDateTime.now());

			// Update account balance
			BigDecimal updatedBalance = account.getBalance().subtract(amount);
			account.setBalance(updatedBalance);

			// Save transaction and account
			transactionRepository.save(transaction);
			accountRepository.save(account);

			return transaction;
		} else {
			// Insufficient balance, throw the custom exception
			throw new InsufficientBalanceException("Insufficient balance in the account");
		}
	}

	public Transaction updateTransaction(Long id, TransactionRequestDTO transactionRequest) {
		Transaction existingTransaction = transactionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Transaction not found"));

		// Update the transaction details based on the TransactionRequestDTO
		// For example:
		existingTransaction.setAmount(transactionRequest.getAmount());
		existingTransaction.setTransactionTime(LocalDateTime.now());

		// Save the updated transaction
		return transactionRepository.save(existingTransaction);
	}

	public ResponseEntity<?> deleteTransaction(Long id) {
		Transaction existingTransaction = transactionRepository.findById(id)
				.orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));

		// Perform any additional checks or business logic before deleting the
		// transaction
		// For example, you may want to check if the transaction can be deleted based on
		// certain conditions.

		// Delete the transaction
		try {
			// Delete the transaction
			transactionRepository.delete(existingTransaction);
			return ResponseEntity.ok("Transaction deleted successfully!");
		} catch (Exception e) {
			// Handle exceptions that might occur during deletion (e.g., database errors)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete transaction");
		}

	}

	public List<Transaction> getAccountStatement(String accountNumber, LocalDate startDate, LocalDate endDate) {
	        // Fetch the account entity from the database based on the account number
	        Account account = accountRepository.findByAccountNumber(accountNumber);

	        // Perform validations if needed (e.g., check if the account exists)

	        // Get the account statement from the transaction repository based on the date range
	        List<Transaction> accountStatement = transactionRepository.findByAccountAndTransactionTimeBetween(account, startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));

	        return accountStatement;
	    }

	public Transaction transferFunds(Long userId, String fromAccountNumber, String toAccountNumber, BigDecimal amount) { Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber); Account toAccount = accountRepository.findByAccountNumber(toAccountNumber); if (fromAccount.getAccountNumber() == null || toAccount == null) { throw new AccountNotFoundException("Account not found."); } if (fromAccount.getBalance().compareTo(amount) < 0) { throw new InsufficientBalanceException("Insufficient balance for transfer."); } 
	fromAccount.setBalance(fromAccount.getBalance().subtract(amount)); toAccount.setBalance(toAccount.getBalance().add(amount)); accountRepository.save(fromAccount); accountRepository.save(toAccount); return null; } }
