package com.anudipgroupproject.socialize.validators;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.anudipgroupproject.socialize.utils.ApplicationContextProvider;
import com.anudipgroupproject.socialize.validators.annotations.Unique;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueValidator implements ConstraintValidator<Unique, Object> {
	private ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

	private FieldValueExists service;
	
	private String fieldName;
	
	@Override
    public void initialize(Unique constraintAnnotation) {
		Class<? extends FieldValueExists> clazz = constraintAnnotation.service();
		String serviceQualifier = constraintAnnotation.serviceQualifier();
		this.fieldName = constraintAnnotation.fieldName();
        if (!serviceQualifier.equals("")) {
            this.service = this.applicationContext.getBean(serviceQualifier, clazz);
        } else {
            this.service = this.applicationContext.getBean(clazz);
        }
    }

	@Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        return !this.service.fieldValueExists(o, this.fieldName);
    }
}
