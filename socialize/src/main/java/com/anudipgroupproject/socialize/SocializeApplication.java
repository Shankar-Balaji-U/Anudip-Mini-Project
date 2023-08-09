package com.anudipgroupproject.socialize;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.module.SimpleModule;

import com.anudipgroupproject.socialize.models.User;
import com.anudipgroupproject.socialize.models.serializer.UserEntitySerializer;
import com.anudipgroupproject.socialize.utils.DataUrlSerializer;

@SpringBootApplication
public class SocializeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocializeApplication.class, args);
	}

	@Bean
	public SimpleModule getModule() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(File.class, new DataUrlSerializer());
//		module.addSerializer(User.class, new UserEntitySerializer());
		return module;
	}

}