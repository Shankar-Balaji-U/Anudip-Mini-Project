package com.anudipgroupproject.socialize.forms;

import static com.anudipgroupproject.socialize.utils.MediaManager.convertMultipartFileToFile;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.anudipgroupproject.socialize.models.User;
import com.anudipgroupproject.socialize.services.UserService;
import com.anudipgroupproject.socialize.validators.annotations.FileSize;
import com.anudipgroupproject.socialize.validators.annotations.Unique;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class UserUpdationForm {
	
	@NotBlank(message="Username is required")
	@Unique(service=UserService.class, fieldName="username", message="Username should be unique.")
	private String username;

	private String displayname;

	@Pattern(regexp="\\d{10}", message="Mobile number must be a 10-digit number")
	private String mobile;

	@NotBlank(message="Email is required")
	@Email(message="Invalid email format")
	private String email;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MultipartFile getImage() {
		if (this.image !=  null)
			System.out.println("UserForm.getImage" + this.image);	

		return this.image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public User getEntity() throws IOException {
		User obj = new User();

		if (this.getUsername() != null) {
			obj.setUsername(this.getUsername());
		}

		if (this.getDisplayname() != null) {
			obj.setDisplayname(this.getDisplayname());
		}

		if (this.getImage() != null) {
			obj.setImage(convertMultipartFileToFile(this.getImage()));
		}

		if (this.getEmail() != null) {
			obj.setEmail(this.getEmail());
		}

		if (this.getMobile() != null) {
			obj.setMobile(this.getMobile());
		}
		return obj;
	}

	public String toString() {
		return String.format("UserForm(username=%s)", username);
	}
}
