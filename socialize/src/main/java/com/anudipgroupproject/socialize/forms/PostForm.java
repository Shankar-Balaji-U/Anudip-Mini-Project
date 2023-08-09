package com.anudipgroupproject.socialize.forms;

import static com.anudipgroupproject.socialize.utils.MediaManager.convertMultipartFileToFile;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.anudipgroupproject.socialize.models.Post;

import com.anudipgroupproject.socialize.models.User;
import com.anudipgroupproject.socialize.validators.annotations.FileSize;

import jakarta.validation.constraints.NotBlank;


public class PostForm {

	private User user;

	@NotBlank(message="Caption is required")
	private String caption;

	@FileSize(max=2097152, message="Image size must be less than 2MB")
	private MultipartFile image;

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

	public MultipartFile getImage() {
		return this.image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public Post getEntity() throws IOException {
		Post obj = new Post();

		if (this.getUser() != null) {
			obj.setUser(this.getUser());
		}

		if (this.getCaption() != null) {
			obj.setCaption(this.getCaption());
		}

		if (!this.getImage().isEmpty()) {
			obj.setImage(convertMultipartFileToFile(this.getImage()));
		}
		return obj;
	}

	public String toString() {
		return String.format("PostForm(caption=%s)", caption);
	}
}