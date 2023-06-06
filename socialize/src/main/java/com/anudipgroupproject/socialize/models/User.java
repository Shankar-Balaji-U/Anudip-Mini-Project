package com.anudipgroupproject.socialize.models;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.anudipgroupproject.socialize.exceptions.PasswordMismatchException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;


@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@Column(name="displayname")
	private String displayname;
	
	@Column(name="password", length=25, nullable=false)
	private String password;
	
	@Column(name="mobile_no", length=20)
	private String mobile_no;

	@Column(name="email_id")
	private String email_id;
	
	@Column(name="profile_image")
	private String image;
	
	@Transient
	private MultipartFile image_file;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on")
	private Date created_on;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_login")
	private Date last_login;
	
	/**
	 * If the user is active in their session, this value will be set to TRUE.
	 * This can be achieved using AJAX. A signal is passed from the client to the server to update this value.
	 */
	@Column(name="is_active", columnDefinition="BIT(1) DEFAULT FALSE")
	private boolean is_active;
	
	/**
	 * Indicates whether the user is deleted or not.
	 * By default, the value is set to FALSE, indicating that the user is not deleted.
	 */
	@Column(name="is_deleted", columnDefinition="BIT(1) DEFAULT FALSE")
	private boolean is_deleted;
	
    @OneToMany(mappedBy="user")
    private List<Post> posts;
	
	@PrePersist
    protected void onCreate() {
		if (this.getDisplayname() == null) {
			this.setDisplayname(this.getUsername());
		}
	}
	
	// Default constructor
    public User() {
    	// Automatically set the date when the object is saved on create
		this.created_on = new Date();
    }
	
	// Getters and setters for the class properties
	public Long getId() {
		return id;
	}
	
	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
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

	public void setPassword(String newPassword, String confirmPassword) {
		if (!newPassword.equals(confirmPassword)) {
			throw new PasswordMismatchException();
		}
		this.password = newPassword;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
	
	
	
	
	
	public MultipartFile getProfileImageFile() {
//		TODO: String to File object
		if (this.image != null) {
			return this.image_file;
		}
		return null;
	}
	
	public void setProfileImageFile(MultipartFile file) {
		this.image_file = file;
	}
	
	public void saveProfileImageFile() {
		if (this.image != null) {
			
		}
	}
	
	public String getProfileImagePath() {
		return image;
	}

	
	
	
	
	
	
	
	
	
	
	public String getEmailId() {
		return email_id;
	}

	public void setEmailId(String email_id) {
		this.email_id = email_id;
		// Validate email format if needed
	}
	
	public String getMobileNo() {
		return mobile_no;
	}

	public void setMobileNo(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public Date getCreatedOn() {
		return created_on;
	}
	
	public void setLastLogin(Date datetime) {
		this.last_login = datetime;
	}
	
	public Date getLastLogin() {
		return last_login;
	}
	
	public boolean getIsActive() {
		return is_active;
	}

	public void setIsActive(boolean is_active) {
		this.is_active = is_active;
	}
	
	public boolean getIsDelete() {
		return is_deleted;
	}
	
	public void setIsDelete(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	
	public List<Post> getPosts() {
		return this.posts;
	}
	
	@Override
    public String toString() {
		return String.format("User(id=%s, username=%s, created_on=%s)", id, username, created_on);
    }
}