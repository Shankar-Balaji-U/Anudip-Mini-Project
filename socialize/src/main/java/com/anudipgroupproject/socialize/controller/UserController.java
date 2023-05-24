package com.anudipgroupproject.socialize.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anudipgroupproject.socialize.models.User;
import com.anudipgroupproject.socialize.services.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	
  @Autowired
  public UserController(UserService userService) {
      this.userService = userService;
  }
  @PostMapping("/new")
  public ResponseEntity<User> createUser(@RequestBody User user) {
	  User createdUser = this.userService.create(user);
	  return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
  }
  @PutMapping("/update/{id}")
  public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
	  User userService = this.userService.update(id, user);
	  return new ResponseEntity<User>(userService, HttpStatus.OK);
  }
  @GetMapping("/get/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
	  User user = this.userService.get(id);
	  return new ResponseEntity<User>(user, HttpStatus.OK);
  }
  @GetMapping("/u/{username}")
  public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
	  User user = this.userService.get(username);
	  return new ResponseEntity<User>(user, HttpStatus.OK);
  }
  @GetMapping("/all")
  public ResponseEntity<List<User>> getAllUser() {
	  List<User> users = this.userService.all();
	  return new ResponseEntity<List<User>>(users, HttpStatus.OK);
  }
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
	  this.userService.delete(id);
	  return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}