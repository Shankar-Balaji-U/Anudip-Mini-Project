package com.anudipgroupproject.socialize.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
			
		}
		
		User user = this.userService.get(username);
		Post obj = new Post(caption, imageFile.getBytes(), user);
		Post post = this.postService.create(obj);
		return new ResponseEntity<String>(post.toString(), HttpStatus.OK);
	}
		
	@PutMapping(value="/{username}/p/edit/{id}", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Post> updatePost(@PathVariable String username, @PathVariable Long id) {
		User user = this.userService.get(username);
		List<Post> posts = user.getPosts();
		Post userPost = null;
		
		for (Post post: posts) {
			if (post.getId() == id) {
				userPost = post;
				break;
			}
		}
		if (userPost == null) {
			throw new ResourceNotFoundException("Post", "id", String.valueOf(id));
		}
		return new ResponseEntity<Post>(userPost, HttpStatus.OK);
	}
	@GetMapping("/{username}/p/all")
	public ResponseEntity<List<Post>> getAllPostByUsername(@PathVariable String username) {
		User user = this.userService.get(username);
		List<Post> posts = user.getPosts();
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/p/{id}")
	public ResponseEntity<Post> getPostByUsername(@PathVariable Long id) {
		Post post = this.postService.get(id);
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
}
