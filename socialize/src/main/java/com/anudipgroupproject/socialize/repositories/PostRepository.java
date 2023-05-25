package com.anudipgroupproject.socialize.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anudipgroupproject.socialize.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}