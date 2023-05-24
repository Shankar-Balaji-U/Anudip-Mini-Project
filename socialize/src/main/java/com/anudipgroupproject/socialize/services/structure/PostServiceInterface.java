package com.anudipgroupproject.socialize.services.structure;

import java.util.List;

import com.anudipgroupproject.socialize.models.Post;

public class PostServiceInterface {
	public User create(User student);
	public User update(Long id, User obj);
	public User get(Long id);
	public User get(String usernname);
	public List<Post> all();
	public void delete(Long id);
}
