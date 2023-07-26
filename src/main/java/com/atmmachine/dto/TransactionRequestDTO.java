package com.atmmachine.dto;

import java.math.BigDecimal;

public class TransactionRequestDTO {
	private String accountNumber;
	private BigDecimal amount;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionRequestDTO() {
		super();
	}

	public TransactionRequestDTO(String accountNumber, BigDecimal amount) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	

	// Constructors, getters, and setters

}
