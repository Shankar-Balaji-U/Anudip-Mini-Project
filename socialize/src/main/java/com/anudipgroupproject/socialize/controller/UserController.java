package com.anudipgroupproject.socialize.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anudipgroupproject.socialize.exceptions.FieldError;
import com.anudipgroupproject.socialize.forms.CheckUserForm;
import com.anudipgroupproject.socialize.forms.UserCreationForm;
import com.anudipgroupproject.socialize.forms.UserUpdationForm;
import com.anudipgroupproject.socialize.models.User;
import com.anudipgroupproject.socialize.services.UserService;


@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/user")
public class UserController extends Common {
	@Autowired
	private UserService userService;

	@PostMapping(value="/create-profile/", consumes={"*/*", MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> createUser(@ModelAttribute UserCreationForm userData) throws IOException {
		this.cleanErrors();

		boolean isExist = this.userService.isExists(userData.getUsername());

		if (isExist)
			this.addError(new FieldError("username", "Username should be unique."));

		this.validateForm(userData);
		
		User newCreatedUser = this.userService.create(userData.getEntity());
		return new ResponseEntity<User>(newCreatedUser, HttpStatus.CREATED);
	}

	@PutMapping(value="/update-profile/{id}/", consumes={"*/*", MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody UserUpdationForm userData) throws IOException {
		this.cleanErrors();
		
		// Retrieve the existing User object from the data source
		User existingUser = this.userService.get(id);
		existingUser.copy(userData.getEntity());

		// Check for validation errors
		this.validateForm(userData);

		User updatedUser = this.userService.update(id, existingUser);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}
	
	@PutMapping(value="/update-password/{id}/", consumes={"*/*", MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> updateUserPassword(@PathVariable long id, @RequestBody String password) {	
		this.cleanErrors();
		
		// Retrieve the existing User object from the data source
		User existingUser = this.userService.get(id);
		existingUser.setPassword(password);

		// Check for validation errors
		this.validateForm(existingUser);

		User updatedUser = this.userService.update(id, existingUser);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

	@PutMapping("/update-activestatus/{id}/")
	public ResponseEntity<String> updateUserActiveStatus(@PathVariable long id, @RequestParam("is_active") boolean is_active) {
		User existingUser = this.userService.updateActiveStatus(id, is_active);
		return new ResponseEntity<String>(existingUser.toString(), HttpStatus.OK);
	}

	@GetMapping(value="/get/by-id/{id}/", consumes={"*/*", MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		User existingUser = this.userService.get(id);
		return new ResponseEntity<User>(existingUser, HttpStatus.OK);
	}

	@GetMapping("/get/by-username/{username}/")
	public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
		User existingUser = this.userService.get(username);
		return new ResponseEntity<User>(existingUser, HttpStatus.OK);
	}

	@PostMapping("isexist-username/")
	public boolean checkExistingUsername(@RequestBody CheckUserForm checkForm) {
		if (checkForm.getId() != null) 
			return this.userService.isExists(checkForm.getId(), checkForm.getUsername());
		return this.userService.isExists(checkForm.getUsername());
	}

	@GetMapping("/get/all/")
	public ResponseEntity<List<User>> allUser() {
		return new ResponseEntity<List<User>>(this.userService.all(), HttpStatus.OK);
	}

	@DeleteMapping("/delete-profile/{id}/")
	public ResponseEntity<?> deleteUser(@PathVariable long id) {
		User deletedUser = this.userService.delete(id);
		return new ResponseEntity<User>(deletedUser, HttpStatus.OK);
	}
}

//POST http://localhost:8080/user/create-profile/
//GET http://localhost:8080/user/get/by-id/{id}/
//GET http://localhost:8080/user/get/by-username/{username}/
//GET http://localhost:8080/user/get/isexist-username/{username}/
//GET http://localhost:8080/user/get/all/
//PUT http://localhost:8080/user/update-profile/{id}/
//PUT http://localhost:8080/user/update-activestatus/1/?is_active=true
//DELETE http://localhost:8080/user/delete-profile/1/



//username:lover_boi_2511
//displayname:Shankar Balaji
//password:shankarbalaji14
//mobile_no:9043383123
//email_id:lover_boi_2511@gmail.com
//image:


