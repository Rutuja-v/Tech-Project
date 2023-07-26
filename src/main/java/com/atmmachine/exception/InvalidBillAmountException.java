package com.atmmachine.exception;

public class InvalidBillAmountException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidBillAmountException() {
        super("Invalid bill amount");
    }
}
