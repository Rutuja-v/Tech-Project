package com.atmmachine.model;

import javax.persistence.*;



import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String accountNumber;

	private BigDecimal balance;

	private String pin;


//	@ManyToOne
@Column(name = "user_id")
private Long userId;

//	private User user;
//	

	public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}
//	public String getNewPIN() {
//		return newPIN;
//	}
//
//	public void setNewPIN(String newPIN) {
//		this.newPIN = newPIN;
//	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
//
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
	public Account() {
		super();
	}

	public Account(Long id, String accountNumber, BigDecimal balance) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	// Deposit method to add funds to the account
	public void deposit(BigDecimal amount) {
		if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0) {
			balance = balance.add(amount);
		}
	}

	// Withdraw method to deduct funds from the account
	public void withdraw(BigDecimal amount) {
		if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0 && balance.compareTo(amount) >= 0) {
			balance = balance.subtract(amount);
		}
	}



	

	// Constructors, getters, and setters

}
