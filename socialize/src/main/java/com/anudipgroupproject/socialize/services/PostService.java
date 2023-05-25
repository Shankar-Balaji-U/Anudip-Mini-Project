package com.anudipgroupproject.socialize.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anudipgroupproject.socialize.exceptions.ResourceNotFoundException;
import com.anudipgroupproject.socialize.models.Post;
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
//    	existingObj.setUsername(obj.getUsername());
//    	existingObj.setDisplayname(obj.getDisplayname());
//    	// existingObj.setRawPassword(obj.getPassword());
//    	existingObj.setPassword(obj.getPassword());
//    	existingObj.setProfileImage(obj.getProfileImage());
//		existingObj.setMobileNo(obj.getMobileNo());
//		existingObj.setMobileNo(obj.getMobileNo());
//		existingObj.setEmailId(obj.getEmailId());
//		existingObj.setIsActive(obj.getIsActive());
		
		// save updated student to DB
		this.objects.save(existingObj);
		return existingObj;
	}
	
	@Override
	public Post get(Long id) {
		return objects.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Post", "id", id));
	}
	
//	@Override
//	public List<Post> all(String username) {
//		return objects.findByUser(username).orElseThrow(() ->  new ResourceNotFoundException("Post", "username", username));
//	}
	
	@Override
	public List<Post> all() {
		return this.objects.findAll();
	}
	
	@Override
	public void delete(Long id) {
		// This will return a object or else it raise a exception
    	this.get(id);
    	this.objects.deleteById(id);
	}
}
