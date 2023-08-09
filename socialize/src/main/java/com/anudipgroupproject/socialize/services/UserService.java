package com.anudipgroupproject.socialize.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anudipgroupproject.socialize.exceptions.ResourceNotFoundException;
import com.anudipgroupproject.socialize.exceptions.ResourceAlreadyDeletedException;
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
		if (existingUser.getIsDeleted()) {
			throw new ResourceAlreadyDeletedException(String.format("This user's id(%d) is already deleted", id));
		}
		existingUser.copy(updateUser);

		// save updated student to DB
		return this.objects.save(existingUser);
	}

	@Override
	public User updateActiveStatus(Long id, boolean is_active) {
		User existingUser = this.get(id);
		if (existingUser.getIsDeleted()) {
			throw new ResourceAlreadyDeletedException(String.format("This user's id(%d) is already deleted", id));
		}
		existingUser.setIsActive(is_active);
		return this.objects.save(existingUser);
	}

	@Override
	public User get(Long id) {
		User user = this.objects.findById(id)
				.orElseThrow(() ->  new ResourceNotFoundException("User", "id", id));
		return user;			
	}

	@Override
	public User get(String username) {
		return this.objects.findByUsername(username)
				.orElseThrow(() ->  new ResourceNotFoundException("User", "username", username));
	}

	@Override
	public List<User> all() {
		return this.objects.findAll().stream().filter(object -> !object.getIsDeleted()).toList();
	}

	@Override
	public User delete(Long id) {
		// This will return a object or else it raise a exception
		User user = this.get(id);
		if (user.getIsDeleted()) {
			throw new ResourceAlreadyDeletedException(String.format("This user's id(%d) is already deleted", id));
		}
		user.setIsDeleted(true);
		return this.objects.save(user);
	}

	@Override
	public boolean isExists(String username) {
		return this.objects.existsByUsername(username);
	}

	@Override
	public boolean isExists(Long id, String username) {
		return this.objects.existsByUsernameAndIdNot(username, id);
	}

	@Override
	public boolean isDeleted(Long id) {
		User user = this.get(id);
		return user.getIsDeleted();
	}

	@Override
	public boolean fieldValueExists(Object value, String fieldName) {
		System.out.println((String) value);
		if (!fieldName.equals("username")) {
			throw new UnsupportedOperationException("Field name not supported");
		}

		return this.objects.existsByUsername((String) value);
	}
}