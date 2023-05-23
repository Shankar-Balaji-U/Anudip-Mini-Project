package com.anudipgroupproject.socialize.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@ManyToOne
    @JoinColumn(name="user", referencedColumnName="id")
    private User user;
	
	@Column(name="caption")
	private String caption;
	
	@Column(name="image", nullable=false, columnDefinition="BLOB")
	private byte[] image;
	
	@Column(name="created_on")
	private Date created_on;
	
	
	@PrePersist
    protected void onCreate() {
		created_on = new Date();
    }
}
