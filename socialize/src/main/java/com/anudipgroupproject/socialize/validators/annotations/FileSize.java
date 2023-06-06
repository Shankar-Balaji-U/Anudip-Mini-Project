package com.anudipgroupproject.socialize.validators.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import com.anudipgroupproject.socialize.validators.FileSizeValidator;

@Documented
@Constraint(validatedBy={FileSizeValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSize {
	public String message() default "File size must be between {min} and {max} bytes";
	
	Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    long min() default 0;

    long max() default Long.MAX_VALUE;
}