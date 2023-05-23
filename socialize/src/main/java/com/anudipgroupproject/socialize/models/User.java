package com.anudipgroupproject.socialize.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="profile_image", nullable=false, columnDefinition="BLOB")
	private byte[] image;
	
	@Column(name="email_id", nullable=false)
	private String email_id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on")
	private Date created_on;
	
	@Column(name="is_active")
	private boolean is_active;
	
	@Column(name="mobile_no", nullable=false, length=20)
	private String mobile_no;
	
	// automatically set the date which object is save on create
	@PrePersist
    protected void onCreate() {
		created_on = new Date();
    }

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getEmailId() {
		return email_id;
	}

	public void setEmailId(String email_id) {
		this.email_id = email_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedOn() {
		return created_on;
	}

	public boolean getIsActive() {
		return is_active;
	}

	public void setIsActive(boolean is_active) {
		this.is_active = is_active;
	}

	public String getMobileNo() {
		return mobile_no;
	}

	public void setMobileNo(String mobile_no) {
		this.mobile_no = mobile_no;
	}
}