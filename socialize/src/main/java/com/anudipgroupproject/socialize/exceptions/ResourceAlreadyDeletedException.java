package com.anudipgroupproject.socialize.exceptions;

public class ResourceAlreadyDeletedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceAlreadyDeletedException(String message) {
        super(message);
    }
}