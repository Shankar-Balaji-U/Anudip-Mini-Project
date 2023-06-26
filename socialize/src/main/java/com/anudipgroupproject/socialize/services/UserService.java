package com.anudipgroupproject.socialize.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.anudipgroupproject.socialize.exceptions.ResourceNotFoundException;
import com.anudipgroupproject.socialize.exceptions.RowAlreadyDeletedException;
import com.anudipgroupproject.socialize.models.User;
import com.anudipgroupproject.socialize.repositories.UserRepository;
import com.anudipgroupproject.socialize.services.structure.UserServiceInterface;
import com.anudipgroupproject.socialize.validators.FieldValueExists;


@Service
public class UserService implements UserServiceInterface, FieldValueExists {
	UserRepository objects;
	
	public UserService(UserRepository objects) {
		super();
		this.objects = objects;
	}
	
	@Override
	public User create(User createUser) {
		return this.objects.save(createUser);
	}
	
	@Override
	public User update(Long id, User updateUser) {
		// we need to check whether Student with given id is exist in DB or not
    	User existingUser = this.get(id);
		
    	existingUser.copy(updateUser);
    	
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
		return this.objects.findById(id)
				.filter(user -> !user.getIsDelete())
				.orElseThrow(() ->  new ResourceNotFoundException("User", "id", id));
	}
	
	@Override
	public User get(String username) {
		return this.objects.findByUsername(username)
				.filter(user -> !user.getIsDelete())
				.orElseThrow(() ->  new ResourceNotFoundException("User", "username", username));
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
    		throw new RowAlreadyDeletedException(String.format("This user's id(%d) is already deleted", id));
    	}
    	user.setIsDelete(true);
    	return this.objects.save(user);
	}
	
	@Override
	public boolean isExists(String username) {
		return this.objects.existsByUsername(username);
	}
	
	@Override
	public boolean isDeleted(Long id) {
		return true;
//		return this.objects.findById(id)
//				.filter(user -> user.getIsDelete());
	}
	
	@Override
	public boolean fieldValueExists(Object value, String fieldName) {
		if (!fieldName.equals("username")) {
            throw new UnsupportedOperationException("Field name not supported");
        }
		
		return this.objects.existsByUsername((String) value);
	}
}