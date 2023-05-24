package com.anudipgroupproject.socialize.models;

import java.util.Date;

import com.anudipgroupproject.socialize.exceptions.NotAnEmailException;
import com.anudipgroupproject.socialize.exceptions.PasswordMismatchException;
import com.anudipgroupproject.socialize.validators.EmailValidator;

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
	/** 
	 * id: Auto 
	 * image: DEFAULT blank
	 * email_id: DEFAULT blank
	 * username: unique
	 * displayname: DEFAULT username
	 * password: ALLOWED CHAR(100)
	 * created_on: DEFAULT NOW()
	 * last_login: DEFAULT blank
	 * is_active: DEFAULT TRUE
	 * */
	

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@Column(name="displayname")
	private String displayname;
	
	@Column(name="password", length=100, nullable=false)
	private String password;
	
	@Column(name="mobile_no", length=20)
	private String mobile_no;

	@Column(name="email_id")
	private String email_id;

	@Column(name="profile_image", columnDefinition="BLOB")
	private byte[] image;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on")
	private Date created_on;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_login")
	private Date last_login;
	
	@Column(name="is_active")
	private boolean is_active;
	
	// automatically set the date which object is save on create
	@PrePersist
    protected void onCreate() {
		this.displayname = this.username;
		this.created_on = new Date();
		this.is_active = true;
    }
	
	// Default constructor
    public User() {
    }
    
	public User(String username,  String newPassword, String confirmPassword) {
		super();
		this.setUsername(username);
		this.setPassword(newPassword, confirmPassword);
	}
	
	public User(String username, String alicename, String newPassword, String confirmPassword, byte[] image, String mobile_no) {
		this(username, newPassword, confirmPassword);
		this.setProfileImage(image);
		this.displayname = alicename;
		this.mobile_no = mobile_no;
	}
	
	public User(String displayname, String username, String newPassword, String confirmPassword, String mobile_no, String email_id, byte[] image) {
		this(username, newPassword, confirmPassword);
		this.setProfileImage(image);
		this.displayname = displayname;
		this.mobile_no = mobile_no;
		this.email_id = email_id;
	}
	
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
	
	public byte[] getProfileImage() {
		return image;
	}

	public void setProfileImage(byte[] image) {
		this.image = image;
	}

	public String getEmailId() {
		return email_id;
	}

	public String getMobileNo() {
		return mobile_no;
	}

	public void setMobileNo(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	
	public void setEmailId(String email_id) {
		try {
			new EmailValidator(email_id);
            this.email_id = email_id;
		} catch (NotAnEmailException e) {
            e.printStackTrace();
		}
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
}