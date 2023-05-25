package com.anudipgroupproject.socialize.services.structure;

import java.util.List;

import com.anudipgroupproject.socialize.models.User;

public interface UserServiceInterface {
	public User create(User obj);
	public User update(Long id, User obj);
	public User get(Long id);
	public User get(String usernname);
	public List<User> all();
	public void delete(Long id);
}