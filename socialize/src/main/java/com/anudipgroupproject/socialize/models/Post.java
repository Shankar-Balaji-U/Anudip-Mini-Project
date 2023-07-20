package com.anudipgroupproject.socialize.models;

import java.io.File;
import java.util.Date;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import com.anudipgroupproject.socialize.models.fields.MediaFile;
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

@Entity
@Table(name = "posts")
public class Post {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch=FetchType.LAZY)  			// @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="user", nullable=false, referencedColumnName="id")
	@JsonIgnore
    private User user;
	
	@Column(name="caption")
	private String caption;
	
	@Type(value=MediaFile.class, parameters={ @Parameter(name="folderName", value="post_images") })
	@Column(name="image", nullable=false)
	private File image;
	
	@Column(name="is_deleted", columnDefinition="BIT(1) DEFAULT FALSE")
	private boolean is_deleted;
	
	@Column(name="created_on")
	private Date created_on;
	
	@PrePersist
    protected void onCreate() {
		this.created_on = new Date();
    }

	public Long getId() {
		return this.id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public File getImage() {
		return this.image;
	}

	public void setImage(File image) {
		this.image = image;
	}
	
	public boolean getIsDeleted() {
		return is_deleted;
	}
	
	public void setIsDeleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Date getCreatedOn() {
		return this.created_on;
	}

	public void copy(Post post) {
		if (post.getUser() != null)
			this.setUser(post.getUser());

		if (post.getImage() != null)
			this.setImage(post.getImage());
		
		if (post.getCaption() != null)
			this.setCaption(post.getCaption());
	}
}
