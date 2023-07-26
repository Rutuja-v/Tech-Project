package com.atmmachine.exception;

public class InsufficientBalanceException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException(String message) {
        super("Insufficient balance to make the payment");
    }
}
