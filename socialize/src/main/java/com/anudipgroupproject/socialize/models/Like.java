package com.anudipgroupproject.socialize.models;

import java.util.Set;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class Like {
	@ManyToOne
    @JoinColumn(name="post", referencedColumnName="id")
	private Post post;
	
	@ManyToMany
	@JoinColumn(name="users", referencedColumnName="id")
//	joinColumns = @JoinColumn(name = "post_id"),
//    inverseJoinColumns = @JoinColumn(name = "user_id")
	private Set <User> users;
}
