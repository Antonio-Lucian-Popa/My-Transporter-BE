package com.asusoftware.myTransporter.post.repository;

import com.asusoftware.myTransporter.post.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Likes, UUID> {
}
