package com.atmmachine.dto;

import java.math.BigDecimal;

public class ContactlessTransactionRequestDTO {
    private String cardNumber;
    private BigDecimal amount;
    private String transactionType; // e.g., "PURCHASE" or "PAYMENT"

    // Constructors, getters, and setters

    public ContactlessTransactionRequestDTO() {
    }

    public ContactlessTransactionRequestDTO(String cardNumber, BigDecimal amount, String transactionType) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
