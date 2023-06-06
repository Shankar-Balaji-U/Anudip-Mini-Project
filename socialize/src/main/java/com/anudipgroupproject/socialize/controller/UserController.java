package com.anudipgroupproject.socialize.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

import com.anudipgroupproject.socialize.exceptions.RowDeletedException;
import com.anudipgroupproject.socialize.forms.UserForm;
import com.anudipgroupproject.socialize.models.User;
import com.anudipgroupproject.socialize.services.UserService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	
  @Autowired
  public UserController(UserService userService) {
      this.userService = userService;
  }
  
  @PostMapping(value="/create-profile", consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<String> createUser(@Valid @ModelAttribute UserForm userData, BindingResult result) {
	  
	  if (result.hasErrors()) {
          // Handle validation errors
          List<ObjectError> errors = result.getAllErrors();
          List<String> responseTexts = new ArrayList<>();
          for (ObjectError error: errors) {
        	  String errorMessage = error.getDefaultMessage();
        	  responseTexts.add(errorMessage);
        	  System.out.println(errorMessage);
          }
          return new ResponseEntity<String>(responseTexts.toString(), HttpStatus.BAD_REQUEST);
	  }
	  
	  User newCreatedUser = this.userService.create(userData.getEntity());
	  return new ResponseEntity<String>(String.format("A new user is created %s", newCreatedUser.toString()), HttpStatus.CREATED);
  }
  
//  @PostMapping("/create-profile")
//  public ResponseEntity<User> createUser(@RequestBody User newUser) {
//	  User newCreatedUser = this.userService.create(newUser);
//	  return new ResponseEntity<User>(newCreatedUser, HttpStatus.CREATED);
//  }
  
  @PutMapping("/update-profile/{id}")
  public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User modifiedUser) {
	  User existingUpdateUser = this.userService.update(id, modifiedUser);
	  return new ResponseEntity<User>(existingUpdateUser, HttpStatus.OK);
  }
  
  @PutMapping("/update-active/{id}")
  public ResponseEntity<User> updateUserActiveStatus(@PathVariable long id, @RequestParam("is_active") boolean is_active) {
	  User existingUpdateUser = this.userService.updateActiveStatus(id, is_active);
	  return new ResponseEntity<User>(existingUpdateUser, HttpStatus.OK);
  }
  
  @GetMapping("/get/id/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
	  User existingUser = this.userService.get(id);
	  return new ResponseEntity<User>(existingUser, HttpStatus.OK);
  }
  
  @GetMapping("get/u/{username}")
  public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
	  User existingUser = this.userService.get(username);
	  return new ResponseEntity<User>(existingUser, HttpStatus.OK);
  }
  
  @GetMapping("/all")
  public ResponseEntity<List<User>> getAllUser() {
	  List<User> existingUsers = this.userService.all();
	  return new ResponseEntity<List<User>>(existingUsers, HttpStatus.OK);
  }
  
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable long id) {
	  String message;
	  try {
		  User deletedUser = this.userService.delete(id);
		  message = String.format("%s is deleted", deletedUser);
	  } catch (RowDeletedException e) {
		  User deletedUser = this.userService.get(id);
		  message = String.format("%s is already deleted", deletedUser);
	  }
	  return new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
  }
}