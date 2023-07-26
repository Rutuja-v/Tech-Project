package com.atmmachine.controller;

import com.atmmachine.dto.AccountDTO;
import com.atmmachine.dto.TransferRequestDTO;
import com.atmmachine.exception.AccountNotFoundException;
import com.atmmachine.exception.InsufficientBalanceException;
import com.atmmachine.model.Account;
import com.atmmachine.model.ChangePINRequest;
import com.atmmachine.repository.AccountRepository;
import com.atmmachine.service.AccountService;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/{accountNumber}")
	public ResponseEntity<?> getAccountByAccountNumber(@PathVariable String accountNumber) {
		AccountDTO accountDTO = accountService.getAccountByAccountNumber(accountNumber);
		if (accountDTO != null) {
			return ResponseEntity.ok(accountDTO);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Account not found with account number: " + accountNumber);
		}
	}

	@PostMapping
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
	
		AccountDTO createdAccount = accountService.createAccount(accountDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
	}

	@PutMapping("/{accountNumber}/deposit")
	public ResponseEntity<?> depositToAccount(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
		AccountDTO updatedAccount = accountService.depositToAccount(accountNumber, amount);
		if (updatedAccount != null) {
			return ResponseEntity.ok(updatedAccount);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Account not found with account number: " + accountNumber);
		}
	}

	@PutMapping("/{accountNumber}/withdraw")
	public ResponseEntity<?> withdrawFromAccount(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
		AccountDTO updatedAccount = accountService.withdrawFromAccount(accountNumber, amount);
		if (updatedAccount != null) {
			return ResponseEntity.ok(updatedAccount);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Account not found with account number: " + accountNumber);
		}
	}

	@DeleteMapping("/{accountNumber}")
	public ResponseEntity<?> deleteAccount(@PathVariable String accountNumber) {
		boolean deleted = accountService.deleteAccount(accountNumber);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account deleted Sucessfully !!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Account not found with account number: " + accountNumber);
		}
	}

	@PutMapping("/{accountNumber}/pin/change")
	public ResponseEntity<?> changePIN(@PathVariable String accountNumber, @RequestBody ChangePINRequest request) {
		boolean pinChanged = accountService.changePIN(accountNumber, request.getPin(), request.getNewPIN());
		if (pinChanged) {
			return ResponseEntity.ok("PIN changed successfully!");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid current PIN or account not found.");
		}
	}
	
	

 @PostMapping("/{fromAccountNumber}/transfer")
    public ResponseEntity<String> transferFunds(@PathVariable String fromAccountNumber, @RequestBody TransferRequestDTO transferRequest) {
        try {
        	System.out.println(transferRequest.getAccountNumber());
            accountService.transferFunds(fromAccountNumber, transferRequest.getAccountNumber(), transferRequest.getAmount());
            return ResponseEntity.ok("Transferred successfully.");
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance for transfer.");
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found.");
        } 
    }
}
