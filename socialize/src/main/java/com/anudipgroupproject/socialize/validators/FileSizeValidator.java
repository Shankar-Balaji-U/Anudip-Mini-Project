package com.anudipgroupproject.socialize.validators;

import org.springframework.web.multipart.MultipartFile;

import com.anudipgroupproject.socialize.validators.annotations.FileSize;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

	private long minSize;
	private long maxSize;

	@Override
	public void initialize(FileSize constraintAnnotation) {
		minSize = constraintAnnotation.min();
		maxSize = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		if (file == null || file.isEmpty()) {
			return true; // Empty file is considered valid
		}
		
		boolean condition = minSize <= file.getSize() && file.getSize() <= maxSize;
		
		return condition;
	}
}
