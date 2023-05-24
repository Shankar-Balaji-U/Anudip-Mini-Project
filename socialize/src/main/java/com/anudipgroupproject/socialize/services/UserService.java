package com.anudipgroupproject.socialize.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anudipgroupproject.socialize.exceptions.ResourceNotFoundException;
import com.anudipgroupproject.socialize.models.User;
import com.anudipgroupproject.socialize.repositories.UserRepository;
import com.anudipgroupproject.socialize.services.structure.UserServiceInterface;


@Service
public class UserService implements UserServiceInterface {
	UserRepository objects;
	
	public UserService(UserRepository objects) {
		super();
		this.objects = objects;
	}
	
	@Override
	public User create(User obj) {
		return this.objects.save(obj);
	}
	
	@Override
	public User update(Long id, User obj) {
		// we need to check whether Student with given id is exist in DB or not
    	User existingObj = this.get(id);
    	existingObj.setUsername(obj.getUsername());
    	existingObj.setDisplayname(obj.getDisplayname());
    	// existingObj.setRawPassword(obj.getPassword());
    	existingObj.setPassword(obj.getPassword());
    	existingObj.setProfileImage(obj.getProfileImage());
		existingObj.setMobileNo(obj.getMobileNo());
		existingObj.setMobileNo(obj.getMobileNo());
		existingObj.setEmailId(obj.getEmailId());
		existingObj.setIsActive(obj.getIsActive());
		
		// save updated student to DB
		this.objects.save(existingObj);
		return existingObj;
	}
	
	@Override
	public User get(Long id) {
		return objects.findById(id).orElseThrow(() ->  new ResourceNotFoundException("User", "id", id));
	}
	
	@Override
	public User get(String username) {
		return objects.findByUsername(username).orElseThrow(() ->  new ResourceNotFoundException("User", "username", username));
	}
	
	@Override
	public List<User> all() {
		return this.objects.findAll();
	}
	
	@Override
	public void delete(Long id) {
		// This will return a object or else it raise a exception
    	this.get(id);
    	this.objects.deleteById(id);
	}
}