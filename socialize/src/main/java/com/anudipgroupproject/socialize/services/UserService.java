package com.anudipgroupproject.socialize.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anudipgroupproject.socialize.exceptions.ResourceNotFoundException;
import com.anudipgroupproject.socialize.exceptions.RowDeletedException;
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
	public User create(User createUser) {
		createUser.saveImageFile();
		return this.objects.save(createUser);
	}
	
	@Override
	public User update(Long id, User updateUser) {
		// we need to check whether Student with given id is exist in DB or not
    	User existingUser = this.get(id);
    	
    	// if the requested serialized objec
		if (updateUser.getUsername() != null) {
			existingUser.setUsername(updateUser.getUsername());
		}
		
		if (updateUser.getDisplayname() != null) {
			existingUser.setDisplayname(updateUser.getDisplayname());
		}
		
		if (updateUser.getPassword() != null) {
		    existingUser.setPassword(updateUser.getPassword());
		}
		
		if (updateUser.getImage() != null) {
		    existingUser.setTempImageFile(updateUser.getTempImageFile());
		    existingUser.saveImageFile();
		}

		if (updateUser.getMobileNo() != null) {
		    existingUser.setMobileNo(updateUser.getMobileNo());
		}
		
		if (updateUser.getEmailId() != null) {
		    existingUser.setEmailId(updateUser.getEmailId());
		}
		
		// save updated student to DB
		return this.objects.save(existingUser);
	}
	
	@Override
	public User updateActiveStatus(Long id, boolean is_active) {
		User existingUser = this.get(id);
		existingUser.setIsActive(is_active);
		return this.objects.save(existingUser);
	}
	
	@Override
	public User get(Long id) {
		return this.objects.findById(id).orElseThrow(() ->  new ResourceNotFoundException("User", "id", id));
	}
	
	@Override
	public User get(String username) {
		return this.objects.findByUsername(username).orElseThrow(() ->  new ResourceNotFoundException("User", "username", username));
	}
	
	@Override
	public List<User> all() {
		return this.objects.findAll();
	}
	
	@Override
	public User delete(Long id) {
		// This will return a object or else it raise a exception
    	User user = this.get(id);
    	if (user.getIsDelete()) {
    		throw new RowDeletedException("User is already deleted");
    	}
    	user.setIsDelete(true);
    	return this.objects.save(user);
	}
}