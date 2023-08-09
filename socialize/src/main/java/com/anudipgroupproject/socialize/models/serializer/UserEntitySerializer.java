package com.anudipgroupproject.socialize.models.serializer;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.anudipgroupproject.socialize.models.User;
import com.anudipgroupproject.socialize.utils.DataUrlSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class UserEntitySerializer extends JsonSerializer<User> {
	@Override
	public void serialize(User entity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField("id", entity.getId());
		jsonGenerator.writeStringField("username", entity.getUsername());
		jsonGenerator.writeStringField("displayname", entity.getDisplayname());
		jsonGenerator.writeStringField("email", entity.getEmail());
		jsonGenerator.writeStringField("mobile", entity.getMobile());

		File image = entity.getImage();
		String imageData = image != null ? DataUrlSerializer.getDataURI(entity.getImage()) : null;
		jsonGenerator.writeStringField("image", imageData);
		jsonGenerator.writeStringField("created_on", entity.getCreatedOn().toString());

		Date lastLogin = entity.getLastLogin();
		String lastLoginDate = lastLogin != null ? lastLogin.toString() : null;
		jsonGenerator.writeStringField("last_login", lastLoginDate);
		jsonGenerator.writeBooleanField("is_active", entity.getIsActive());
		jsonGenerator.writeBooleanField("is_deleted", entity.getIsDeleted());

		jsonGenerator.writeEndObject();
	}
}
