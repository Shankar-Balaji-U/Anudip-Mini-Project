package com.anudipgroupproject.socialize.exceptions;

public class PasswordMismatchException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordMismatchException() {
        super("New password and confirm password do not match");
    }
	
	public PasswordMismatchException(String message) {
        super(message);
    }
}
