package com.anudipgroupproject.socialize.controller;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.anudipgroupproject.socialize.exceptions.ErrorStack;
import com.anudipgroupproject.socialize.exceptions.FieldError;
import com.anudipgroupproject.socialize.exceptions.ValidationErrorException;


public class Common {

	@Autowired
	public Validator validator;

	private ErrorStack errors = new ErrorStack();

	public void addError(Object error) {
		this.errors.add(error);
	}

	public ErrorStack getErrors() {
		return this.errors;
	}

	public void setErrors(ErrorStack errors) {
		this.errors = errors;
	}

	public void cleanErrors() {
		this.errors.clear();
	}

	public final <T> void validateForm(T form) {
		// vatidating the form errors
		Set<ConstraintViolation<T>> violations = this.validator.validate(form);

		// Check for validation errors
		if (!violations.isEmpty()) {
			for(ConstraintViolation<T> violation: violations) {
				String fieldName = violation.getPropertyPath().toString();
				errors.add(new FieldError(fieldName, violation.getMessage()));
			}
		}

		if (!errors.isEmpty())
			throw new ValidationErrorException(errors);		
	}
}
