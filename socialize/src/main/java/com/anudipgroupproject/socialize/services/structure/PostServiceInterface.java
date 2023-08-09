package com.anudipgroupproject.socialize.services.structure;

import java.util.List;

import com.anudipgroupproject.socialize.models.Post;
import com.anudipgroupproject.socialize.models.User;

public interface PostServiceInterface {
	public Post create(Post obj);
	public Post update(Long id, Post obj);
	public Post get(Long id);
	public Post get(Long id, User user);
//	public List<Post> all(String usernname);  we can get this by List<Post> posts = user.getPosts()
	public List<Post> all();
	public Post delete(Long id);
	public Post delete(Long id, User user);
}
