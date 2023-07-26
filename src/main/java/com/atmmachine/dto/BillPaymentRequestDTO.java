package com.atmmachine.dto;

import java.math.BigDecimal;

public class BillPaymentRequestDTO {
    private String accountNumber;
    private BigDecimal billAmount;
    private String billType;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public BillPaymentRequestDTO(String accountNumber, BigDecimal billAmount, String billType) {
		super();
		this.accountNumber = accountNumber;
		this.billAmount = billAmount;
		this.billType = billType;
	}

    // Getters and setters

    // Constructors
    
}
