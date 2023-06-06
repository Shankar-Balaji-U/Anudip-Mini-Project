package com.anudipgroupproject.socialize.models;

import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import com.anudipgroupproject.socialize.utils.MediaManager;

@Entity
@Table(name = "posts")
public class Post {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch=FetchType.LAZY)  //	@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="user", nullable=false, referencedColumnName="id")
	@JsonIgnore
    private User user;
	
	@Column(name="caption")
	private String caption;
	
	@Column(name="image", nullable=false)
	private String image;
	
	@Column(name="created_on")
	private Date created_on;
	
	
	@PrePersist
    protected void onCreate() {
		created_on = new Date();
    }
	
	public Post() { }
	
	public Post(String caption, MultipartFile image) {
		this.setCaption(caption);
		this.setImage(image);
	}
	
	public Post(String caption, MultipartFile image, User user) {
		this(caption, image);
		this.setUser(user);
	}
	
	public long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		String subfolder = this.getUser().getUsername();
		try {
			MediaManager.save(image, subfolder, "post_images");
		} catch (IOException e) {
			
		}
//		this.image = image.ge;
	}

	public Date getCreated_on() {
		return created_on;
	}
}
