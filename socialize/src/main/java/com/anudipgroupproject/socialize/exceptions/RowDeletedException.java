package com.anudipgroupproject.socialize.exceptions;

public class RowDeletedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RowDeletedException(String message) {
        super(message);
    }
}