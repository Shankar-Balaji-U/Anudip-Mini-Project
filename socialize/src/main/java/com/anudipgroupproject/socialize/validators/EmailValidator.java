package com.anudipgroupproject.socialize.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.anudipgroupproject.socialize.exceptions.NotAnEmailException;

public class EmailValidator {
    private static final String EMAIL_PATTERN = (
    	"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + 
    	"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    );

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public EmailValidator(String value) {
    	if (!this.isValidEmail(value)) {
            throw new NotAnEmailException(value);
        }
    }
}