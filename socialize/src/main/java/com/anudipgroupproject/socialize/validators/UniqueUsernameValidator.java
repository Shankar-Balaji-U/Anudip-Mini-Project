package com.anudipgroupproject.socialize.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.anudipgroupproject.socialize.repositories.UserRepository;
import com.anudipgroupproject.socialize.validators.annotations.UniqueUsername;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
	@Autowired UserRepository userRepository;
	
	@Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !userRepository.existsByUsername(name);
    }
}
