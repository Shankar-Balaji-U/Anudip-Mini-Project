package com.anudipgroupproject.socialize.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anudipgroupproject.socialize.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}