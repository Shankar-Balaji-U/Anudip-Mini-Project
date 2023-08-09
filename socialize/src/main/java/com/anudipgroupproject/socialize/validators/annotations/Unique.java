package com.anudipgroupproject.socialize.validators.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.anudipgroupproject.socialize.validators.FieldValueExists;
import com.anudipgroupproject.socialize.validators.UniqueValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

enum E{UserUpdationForm};

@Documented
@Constraint(validatedBy={UniqueValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
	public String message() default "This value is already exist.";
	
	Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    Class<? extends FieldValueExists> service();
    
    String serviceQualifier() default "";
    
    Class<?> clazz() default Object.class;
    
    long id() default -1L;
    
    String fieldName();
}