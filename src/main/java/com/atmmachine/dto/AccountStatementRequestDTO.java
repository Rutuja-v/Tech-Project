package com.atmmachine.dto;

import java.time.LocalDate;

//AccountStatementRequestDTO.java
public class AccountStatementRequestDTO {
 private String accountNumber;
 private LocalDate startDate;
 private LocalDate endDate;

 public String getAccountNumber() {
     return accountNumber;
 }

 public void setAccountNumber(String accountNumber) {
     this.accountNumber = accountNumber;
 }

 public LocalDate getStartDate() {
     return startDate;
 }

 public void setStartDate(LocalDate startDate) {
     this.startDate = startDate;
 }

 public LocalDate getEndDate() {
     return endDate;
 }

 public void setEndDate(LocalDate endDate) {
     this.endDate = endDate;
 }
}
