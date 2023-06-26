package com.anudipgroupproject.socialize.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anudipgroupproject.socialize.exceptions.ResourceNotFoundException;
import com.anudipgroupproject.socialize.exceptions.RowAlreadyDeletedException;
import com.anudipgroupproject.socialize.forms.UserForm;
import com.anudipgroupproject.socialize.models.User;
import com.anudipgroupproject.socialize.services.UserService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private Validator validator;

	

	@PostMapping(value="/create-profile/", consumes={"*/*", MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> createUser(@ModelAttribute UserForm userData) throws IOException {
//		lover_boi_2511
		
		
		// vatidating the form errors
		Set<ConstraintViolation<UserForm>> violations = this.validator.validate(userData);
		
		// Check for validation errors
		if (!violations.isEmpty()) {
			List<String> errors = new ArrayList<String>();
			for(ConstraintViolation<UserForm> violation: violations) {
				errors.add(violation.getMessage());
			}
			// Handle validation errors
			return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
		}

		User newCreatedUser = this.userService.create(userData.getEntity());
		
		return new ResponseEntity<String>(String.format("A new user is created %s", newCreatedUser.toString()), HttpStatus.CREATED);
	}

	
//	
//	username:lover_boi_2511
//	displayname:Shankar Balaji
//	password:shankarbalaji14
//	mobile_no:9043383123
//	email_id:lover_boi_2511@gmail.com
//	image:

	
	
	
	@PutMapping(value="/update-profile/{id}/", consumes={"*/*", MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> updateUser(@PathVariable long id, @ModelAttribute UserForm userData) throws IOException {
		// Retrieve the existing User object from the data source
		User existingUser = this.userService.get(id);
		existingUser.copy(userData.getEntity());
		
		// vatidating the form errors
		Set<ConstraintViolation<User>> violations = this.validator.validate(existingUser);

		// Check for validation errors
		if (!violations.isEmpty()) {
			List<String> errors = new ArrayList<String>();
			for(ConstraintViolation<User> violation: violations) {
				errors.add(violation.getMessage());
			}
			// Handle validation errors
			return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
		}
		
		//		User updatedUser = this.userService.update(id, userData.getEntity());
		return new ResponseEntity<String>(String.format("A existing user is updated %s", existingUser.toString()), HttpStatus.OK);
	}

	@PutMapping("/update-activestatus/{id}/")
	public ResponseEntity<String> updateUserActiveStatus(@PathVariable long id, @RequestParam("is_active") boolean is_active) {
		try {
			User existingUser = this.userService.updateActiveStatus(id, is_active);
			return new ResponseEntity<String>(existingUser.toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping({"/update-profile/{id}/", "/get/by-id/{id}/"})
	public ResponseEntity<String> getUserById(@PathVariable Long id) {
		try {
			User existingUser = this.userService.get(id);
			return new ResponseEntity<String>(existingUser.toString(), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/get/by-username/{username}/")
	public ResponseEntity<String> getUserByUsername(@PathVariable String username) {
		try {
			User existingUser = this.userService.get(username);
			return new ResponseEntity<String>(existingUser.toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/get/all/")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> existingUsers = this.userService.all();
		return new ResponseEntity<List<User>>(existingUsers, HttpStatus.OK);
	}

	@DeleteMapping("/delete-profile/{id}/")
	public ResponseEntity<String> deleteStudent(@PathVariable long id) {
		try {
			User deletedUser = this.userService.delete(id);
			return new ResponseEntity<String>(String.format("%s is deleted", deletedUser), HttpStatus.OK);
		} catch (RowAlreadyDeletedException e) {
			User deletedUser = this.userService.get(id);
			return new ResponseEntity<String>(String.format("%s is already deleted", deletedUser), HttpStatus.BAD_REQUEST);
		}
	}

	public Set<ConstraintViolation<Object>> getVatidation(Object form) {
		// Create a Validator instance
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		return validator.validate(form);
	}
}

//POST http://localhost:8080/user/create-profile/
//GET http://localhost:8080/user/get/by-id/{id}/
//GET http://localhost:8080/user/get/by-username/{username}/
//GET http://localhost:8080/user/get/all/
//PUT http://localhost:8080/user/update-profile/{id}/
//PUT http://localhost:8080/user/update-activestatus/1/?is_active=true
//DELETE http://localhost:8080/user/delete-profile/1/