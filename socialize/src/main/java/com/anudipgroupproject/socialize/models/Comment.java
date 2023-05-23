package com.anudipgroupproject.socialize.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class Comment {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name="post", referencedColumnName="id")
	private Post post;
	
	@ManyToMany
	@JoinColumn(name="user", referencedColumnName="id")
	private User user;

    @Column(name="content", length=500)
	private String content;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
