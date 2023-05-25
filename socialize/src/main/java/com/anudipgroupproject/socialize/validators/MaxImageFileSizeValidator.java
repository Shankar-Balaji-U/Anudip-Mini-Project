package com.anudipgroupproject.socialize.validators;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.anudipgroupproject.socialize.exceptions.MaximimFileSizeException;

public class MaxImageFileSizeValidator {
	private long maximumFileSizeInBytes = 100 * 1024 * 1024; // 100MB
	private long maxFileSize; // MB

	public MaxImageFileSizeValidator(int setSize) throws MaximimFileSizeException {
		long fileSize = setSize * 1024 * 1024; // Convert to bytes
		
		if (this.maximumFileSizeInBytes < fileSize) {
			throw new MaximimFileSizeException(setSize);
		}
		this.maxFileSize = fileSize;
	}

	private boolean isValidSize(Long size) {
		return size < this.maxFileSize;
	}

	/**
	 * @Parm size pass the bytes value of the file
	 * @return boolean value
	 */
	public boolean isValid(Long size) { // bytes
		return this.isValidSize(size);
	}
	
	/**
	 * @Parm file This is a MultipartFile object
	 * @return boolean value
	 */
	public boolean isValid(MultipartFile file) {
		return this.isValidSize(file.getSize());
	}

	/**
	 * @Parm file This is a File object
	 * @return boolean value
	 */
	public boolean isValid(File file) {
		return this.isValidSize(file.length());
	}
}