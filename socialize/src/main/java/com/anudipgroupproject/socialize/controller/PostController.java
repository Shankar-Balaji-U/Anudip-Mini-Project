package com.anudipgroupproject.socialize.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.anudipgroupproject.socialize.exceptions.ResourceNotFoundException;
import com.anudipgroupproject.socialize.models.Post;
import com.anudipgroupproject.socialize.models.User;
import com.anudipgroupproject.socialize.services.PostService;
import com.anudipgroupproject.socialize.services.UserService;
import com.anudipgroupproject.socialize.validators.MaxImageFileSizeValidator;


@RestController
@RequestMapping
public class PostController {
	private final PostService postService;
	private final UserService userService;
	
	@Autowired
	public PostController(PostService postService, UserService userService) {
		this.userService = userService;
		this.postService = postService;
	}
	
	@PostMapping(value="/{username}/p/new/", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> createPost(
			@RequestParam("caption") String caption, 
			@RequestParam("image") MultipartFile imageFile,
			@PathVariable String username) throws IOException {
		
		try {
			MaxImageFileSizeValidator fileSizeValidator = new MaxImageFileSizeValidator(2);  // 2 MB
			if (fileSizeValidator.isValid(imageFile)) {
				// TODO: remaind the user to upload the file 
			}
		} catch (Exception e) {
			// do nothing
		}
		
		User user = this.userService.get(username);
		Post obj = new Post(caption, imageFile, user);
		Post post = this.postService.create(obj);
		return new ResponseEntity<String>(post.toString(), HttpStatus.OK);
	}
	
	@PutMapping(value="/{username}/p/edit/{id}", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Post> updatePost(
			@PathVariable Long id,
			@RequestParam("caption") String caption, 
			@RequestParam("image") MultipartFile imageFile,
			@PathVariable String username) throws IOException {
		User user = this.userService.get(username);
		List<Post> posts = user.getPosts();
		Post userPost = null;
		
		for (Post post: posts) {
			if (post.getId() == id) {
				userPost = post;
				break;
			}
		}
		if (userPost != null) {
			Post post = this.postService.update(id, new Post(caption, imageFile));
			return new ResponseEntity<Post>(post, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Post", "id", id);
		}
	}
	
	@GetMapping("/{username}/p/all")
	public ResponseEntity<List<Post>> getAllPostByUsername(@PathVariable String username) {
		User user = this.userService.get(username);
		System.out.println();
//		List<Post> posts = user.getPosts(); //user.getPosts();
		return new ResponseEntity<List<Post>>(user.getPosts(), HttpStatus.OK);
	}
	
	@DeleteMapping("/p/delete/{id}")
	public ResponseEntity<String> getPostByUsername(@PathVariable Long id) {
		this.postService.delete(id);
		return new ResponseEntity<String>("Object is deleted", HttpStatus.OK);
	}
	
}
