package com.atmmachine.controller;

import com.atmmachine.dto.AccountStatementRequestDTO;
import com.atmmachine.dto.BillPaymentRequestDTO;
import com.atmmachine.dto.ContactlessTransactionRequestDTO;
import com.atmmachine.dto.TransactionRequestDTO;
import com.atmmachine.dto.TransferRequestDTO;
import com.atmmachine.exception.AccountNotFoundException;
import com.atmmachine.exception.BillPaymentException;
import com.atmmachine.exception.InsufficientBalanceException;
import com.atmmachine.exception.InvalidBillAmountException;
import com.atmmachine.exception.InvalidBillTypeException;
import com.atmmachine.exception.TransactionNotFoundException;
import com.atmmachine.model.Account;

import com.atmmachine.model.Transaction;
import com.atmmachine.model.User;
import com.atmmachine.repository.AccountRepository;
import com.atmmachine.repository.UserRepository;
import com.atmmachine.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private final TransactionService transactionService;
	private final AccountRepository accountRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public TransactionController(TransactionService transactionService, AccountRepository accountRepository, UserRepository userRepository) {
		this.transactionService = transactionService;
		this.accountRepository= accountRepository;
		this.userRepository=userRepository;
	
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<String> handle(InsufficientBalanceException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<String> handleTransactionNotFoundException(TransactionNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@PostMapping
	public Transaction createTransaction(@RequestBody TransactionRequestDTO transactionRequest) {
		return transactionService.createTransaction(transactionRequest);
	}

	@DeleteMapping("/{id}")
	public void deleteTransaction(@PathVariable Long id) {
		transactionService.deleteTransaction(id);
	}

	@PostMapping("/statement")
	public ResponseEntity<List<Transaction>> getAccountStatement(
			@RequestBody AccountStatementRequestDTO statementRequest) {
		String accountNumber = statementRequest.getAccountNumber();
		LocalDate startDate = statementRequest.getStartDate();
		LocalDate endDate = statementRequest.getEndDate();

		// Perform validations if needed (e.g., check if the account exists)

		// Get the account statement from the service
		List<Transaction> accountStatement = transactionService.getAccountStatement(accountNumber, startDate, endDate);

		// Return the account statement in the response
		return ResponseEntity.ok(accountStatement);
	}

	@PostMapping("/{userId}/transfer") public ResponseEntity<?> transferFunds(@PathVariable Long userId, @RequestBody TransferRequestDTO request) { try { 
		 Account toAccount = accountRepository.findByUserIdAndAccountNumberNot(userId, request.getAccountNumber()); if (toAccount == null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body("To account not found for the given UserId."); }  
		 Transaction transaction = transactionService.transferFunds(userId, request.getAccountNumber(), toAccount.getAccountNumber(), request.getAmount()); return ResponseEntity.status(HttpStatus.OK).body("Amount Transferred Sucessfully."); } catch (InsufficientBalanceException ex) { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance in the from account."); } catch (TransactionNotFoundException ex) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found."); } catch (Exception ex) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to transfer funds: " + ex.getMessage()); } 
	}
	
	}

	  

