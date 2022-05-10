package com.asusoftware.myTransporter.post.repository;

import com.asusoftware.myTransporter.post.model.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("SELECT p FROM Posts p  WHERE p.user.id=?1")
    List<Post> findAllPostsByPage(UUID userId, PageRequest PageRequest);
}
