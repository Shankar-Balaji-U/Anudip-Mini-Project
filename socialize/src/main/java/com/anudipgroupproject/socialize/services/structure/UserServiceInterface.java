package com.anudipgroupproject.socialize.services.structure;

import java.util.List;

import com.anudipgroupproject.socialize.models.User;

public interface UserServiceInterface {
	public User create(User obj);
	public User update(Long id, User obj);
	public User updateActiveStatus(Long id, boolean is_active);
	public User get(Long id);
	public User get(String username);
	public List<User> all();
	public User delete(Long id);
	public boolean isExists(String username);
	public boolean isExists(Long id, String username);
	public boolean isDeleted(Long id);
}