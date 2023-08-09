package com.anudipgroupproject.socialize.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.anudipgroupproject.socialize.exceptions.ResourceNotFoundException;
import com.anudipgroupproject.socialize.exceptions.ResourceAlreadyDeletedException;
import com.anudipgroupproject.socialize.exceptions.ValidationErrorException;

@ControllerAdvice
public class ControllerAdvisor {
	@ExceptionHandler(ResourceAlreadyDeletedException.class)
	public ResponseEntity<Object> handleRowAlreadyDeletedException(
			ResourceAlreadyDeletedException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();

		body.put("code", "row already deleted");
		body.put("message", ex.getMessage());
		body.put("timestamp", LocalDateTime.now());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
		Map<String, Object> body = new LinkedHashMap<>();

		body.put("code", "resource not found");
		body.put("message", ex.getMessage());
		body.put("timestamp", LocalDateTime.now());

		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ValidationErrorException.class)
	public ResponseEntity<?> handleValidationErrorException(ValidationErrorException ex) {
		Map<String, Object> body = new LinkedHashMap<>();

		body.put("code", "validation error");
		body.put("errors", ex.getErrors());
		body.put("timestamp", LocalDateTime.now());

		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.NOT_FOUND);
	}
}
