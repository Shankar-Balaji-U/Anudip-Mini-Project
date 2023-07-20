package com.anudipgroupproject.socialize.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
public class UnauthorizedAccessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnauthorizedAccessException(String message) {
        super(message);
    }
}