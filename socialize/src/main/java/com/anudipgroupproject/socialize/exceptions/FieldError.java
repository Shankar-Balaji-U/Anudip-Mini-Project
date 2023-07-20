package com.anudipgroupproject.socialize.exceptions;

public class FieldError {
	private String field;
    private String message;
    
    public FieldError(String field_name, String error_message) {
    	this.field = field_name;
    	this.message = error_message;
    }

	public String getField() {
		return field;
	}

	public void setField(String field_name) {
		this.field = field_name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String error_message) {
		this.message = error_message;
	}
}
