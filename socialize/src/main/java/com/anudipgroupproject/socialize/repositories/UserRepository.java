package com.anudipgroupproject.socialize.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anudipgroupproject.socialize.models.User;


// JpaRepository<"Table Datatype", "Primary Key Datatype">
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);
	boolean existsByUsernameAndIdNot(String username, Long id);
}

