package com.anudipgroupproject.socialize.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class ValidationErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private ErrorStack errors;
	
	public ValidationErrorException(ErrorStack errors) {
		this.errors = errors;
	}
	
	public ValidationErrorException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ErrorStack getErrors() {
		return this.errors;
	}
}








//
//
//package com.anudipgroupproject.socialize.exceptions;
//import org.springframework.http.HttpStatus;
//
//
//
//public class ResourceNotFoundException extends RuntimeException {
//	private static final long serialVersionUID = 1L;
//
//	public ResourceNotFoundException(String message) {
//        super(message);
//    }
//
//    public ResourceNotFoundException(String message, Throwable cause) {
//        super(message, cause);
//    }
//    
//    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
//        super(String.format("%s not found with this %s '%s'", resourceName, fieldName, String.valueOf(fieldValue)));
//    }
//}