package com.anudipgroupproject.socialize.models;

import java.util.Date;

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

@Entity
@Table(name = "posts")
public class Post {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch=FetchType.LAZY)  //	@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="user", nullable=false, referencedColumnName="id")
    private User user;
	
	@Column(name="caption")
	private String caption;
	
	@Column(name="image", nullable=false, columnDefinition="LONGBLOB")
	private byte[] image;
	
	@Column(name="created_on")
	private Date created_on;
	
	
	@PrePersist
    protected void onCreate() {
		created_on = new Date();
    }
	
	public Post() {
	}
	
	public Post(String caption, byte[] image, User user) {
		this.caption = caption;
		this.image = image;
		this.user = user;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getCreated_on() {
		return created_on;
	}
}
