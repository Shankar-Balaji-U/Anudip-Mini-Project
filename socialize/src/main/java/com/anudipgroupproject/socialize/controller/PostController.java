package com.anudipgroupproject.socialize.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.anudipgroupproject.socialize.exceptions.FieldError;
import com.anudipgroupproject.socialize.exceptions.ResourceNotFoundException;
import com.anudipgroupproject.socialize.forms.PostForm;
import com.anudipgroupproject.socialize.models.Post;
import com.anudipgroupproject.socialize.models.User;
import com.anudipgroupproject.socialize.services.PostService;
import com.anudipgroupproject.socialize.services.UserService;
import com.anudipgroupproject.socialize.validators.MaxImageFileSizeValidator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


@RestController
@RequestMapping("")
public class PostController extends Common {
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/{username}/p/new/", consumes={"*/*", MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> createPost(@ModelAttribute PostForm postData, @PathVariable String username) throws IOException {
		User user = this.userService.get(username);
		postData.setUser(user);
		this.validateForm(postData);
		
		Post newCreatedPost = this.postService.create(postData.getEntity());
		return new ResponseEntity<Post>(newCreatedPost, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{username}/p/update/{id}/", consumes={"*/*", MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> updatePost(
			@ModelAttribute PostForm postData, 
			@PathVariable Long id, 
			@PathVariable String username) throws IOException {
		
		User user = this.userService.get(username);
		Post existingUserPost = user.getPosts().get(id);
		
		existingUserPost.copy(postData.getEntity());
		this.validateForm(existingUserPost);
		
		existingUserPost = this.postService.update(id, existingUserPost);
		return new ResponseEntity<Post>(existingUserPost, HttpStatus.OK);
	}
	
	@GetMapping("/{username}/p/{id}/")
	public ResponseEntity<?> getPostWithUsername(@PathVariable String username, @PathVariable Long id) {
		User user = this.userService.get(username);
		
		Post post = this.postService.get(id, user);
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	@GetMapping("/p/all/")
	public ResponseEntity<?> getAllPost() {
		List<Post> posts = this.postService.all();
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/{username}/p/all/")
	public ResponseEntity<?> getAllPostByUsername(@PathVariable String username) {
		User user = this.userService.get(username);
		return new ResponseEntity<Collection<Post>>(user.getPosts().values(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{username}/p/delete/{id}/")
	public ResponseEntity<?> deletePostWithUsername(@PathVariable String username, @PathVariable Long id) {
		User user = this.userService.get(username);
		Post post = this.postService.delete(id, user);
		
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
}
