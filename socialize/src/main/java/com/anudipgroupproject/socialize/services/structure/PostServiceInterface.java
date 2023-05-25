package com.anudipgroupproject.socialize.services.structure;

import java.util.List;

import com.anudipgroupproject.socialize.models.Post;

public interface PostServiceInterface {
	public Post create(Post obj);
	public Post update(Long id, Post obj);
	public Post get(Long id);
//	public List<Post> all(String usernname);
	public List<Post> all();
	public void delete(Long id);
}
