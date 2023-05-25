package com.anudipgroupproject.socialize.exceptions;


public class MaximimFileSizeException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public MaximimFileSizeException(int value) {
        super(String.format("Allowed file size is '%s'", String.valueOf(value)));
    }

    public MaximimFileSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}