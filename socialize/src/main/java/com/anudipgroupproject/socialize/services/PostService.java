package com.anudipgroupproject.socialize.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anudipgroupproject.socialize.exceptions.ResourceNotFoundException;
import com.anudipgroupproject.socialize.exceptions.ResourceAlreadyDeletedException;
import com.anudipgroupproject.socialize.exceptions.UnauthorizedAccessException;
import com.anudipgroupproject.socialize.models.Post;
import com.anudipgroupproject.socialize.models.User;
import com.anudipgroupproject.socialize.repositories.PostRepository;
import com.anudipgroupproject.socialize.services.structure.PostServiceInterface;


@Service
public class PostService implements PostServiceInterface {
	PostRepository objects;

	public PostService(PostRepository objects) {
		super();
		this.objects = objects;
	}

	@Override
	public Post create(Post obj) {
		return this.objects.save(obj);
	}

	@Override
	public Post update(Long id, Post obj) {
		// we need to check whether Student with given id is exist in DB or not
		Post existingObj = this.get(id);

		if (existingObj.getIsDeleted())
			throw new ResourceAlreadyDeletedException(String.format("This post's id(%d) is already deleted", id));

		// save updated student to DB
		this.objects.save(existingObj);
		return existingObj;
	}

	@Override
	public Post get(Long id) {
		return objects.findById(id)
				.orElseThrow(() ->  new ResourceNotFoundException("Post", "id", id));
	}

	@Override
	public Post get(Long id, User user) {
		Post post = objects.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

		// Check if the user of the post is not the owner
		if (!post.getUser().equals(user)) {
			throw new UnauthorizedAccessException("User is not the owner of the post");
		}

		return post;
	}

	//	@Override
	//	public List<Post> all(String username) {
	//		return objects.findByUser(username).orElseThrow(() ->  new ResourceNotFoundException("Post", "username", username));
	//	}

	@Override
	public List<Post> all() {
		return this.objects.findAll().stream().filter(object -> !object.getIsDeleted()).toList();
	}

	@Override
	public Post delete(Long id) {// This will return a object or else it raise a exception
		Post post = this.get(id);
		if (post.getIsDeleted())
			throw new ResourceAlreadyDeletedException(String.format("This post's id(%d) is already deleted", id));

		post.setIsDeleted(true);
		return this.objects.save(post);
	}

	@Override
	public Post delete(Long id, User user) {
		// This will return a object or else it raise a exception
		Post post = this.get(id, user);
		if (post.getIsDeleted()) 
			throw new ResourceAlreadyDeletedException(String.format("This post's id(%d) is already deleted", id));

		post.setIsDeleted(true);
		return this.objects.save(post);
	}
}