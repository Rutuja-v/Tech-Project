package com.atmmachine.exception;
public class BillPaymentException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BillPaymentException() {
        super("Failed to process bill payment");
    }
}

