package com.atmmachine.exception;

public class InvalidBillTypeException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidBillTypeException() {
        super("Invalid bill type");
    }
}
