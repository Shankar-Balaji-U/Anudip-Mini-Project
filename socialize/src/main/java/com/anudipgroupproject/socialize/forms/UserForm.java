package com.anudipgroupproject.socialize.forms;

import static com.anudipgroupproject.socialize.utils.MediaManager.convertMultipartFileToFile;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

//import org.springframework.web.multipart.MultipartFile;

import com.anudipgroupproject.socialize.models.User;
import com.anudipgroupproject.socialize.services.UserService;
import com.anudipgroupproject.socialize.validators.annotations.FileSize;
//import com.anudipgroupproject.socialize.validators.annotations.FileSize;
import com.anudipgroupproject.socialize.validators.annotations.Unique;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserForm {
    @NotBlank(message="Username is required")
    @Unique(service=UserService.class, fieldName="username", message="Username should be unique.")
    private String username;

    private String displayname;

	@NotBlank(message="Password is required")
    @Size(min=8, message="Password must be at least 8 characters long")
    private String password;

    @Pattern(regexp="\\d{10}", message="Mobile number must be a 10-digit number")
    private String mobileNo;
    
    @NotBlank(message="Email is required")
    @Email(message="Invalid email format")
    private String emailId;

    @FileSize(max=2097152, message="Image size must be less than 2MB")
    private MultipartFile image;
    
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	public User getEntity() throws IOException {
		User obj = new User();
		
		if (this.getUsername() != null) {
			obj.setUsername(this.getUsername());
		}
		
		if (this.getPassword() != null) {
			obj.setPassword(this.getPassword());
		}
		
		if (this.getDisplayname() != null) {
			obj.setDisplayname(this.getDisplayname());
		}
		
		if (!this.getImage().isEmpty()) {
			obj.setImage(convertMultipartFileToFile(this.getImage()));
		}
		
		if (this.getEmailId() != null) {
			obj.setEmailId(this.getEmailId());
		}
		
		if (this.getMobileNo() != null) {
			obj.setMobileNo(this.getMobileNo());
		}
		return obj;
	}

	public String toString() {
		return String.format("UserForm(username=%s)", username);
	}
}