package com.asusoftware.myTransporter.post.controller;

import com.asusoftware.myTransporter.post.model.dto.CreatePostDto;
import com.asusoftware.myTransporter.post.model.dto.PostDto;
import com.asusoftware.myTransporter.post.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(path = "/create/{userId}")
    public void create(@PathVariable(name = "userId") UUID userId, @RequestBody CreatePostDto createPostDto) {
        postService.create(userId, createPostDto);
    }

    @GetMapping(path = "/findAllPosts/{userId}/{pageNumber}")
    public ResponseEntity<List<PostDto>> findAllPostsByPage(@PathVariable(name = "userId") UUID userId, @PathVariable(name = "pageNumber") int pageNumber) {
        return postService.findAllPostsByPage(userId, pageNumber);
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<PostDto>> findAll() {
        return postService.findAll();
    }

    @PutMapping(path = "/like-post/{userId}/{postId}")
    public ResponseEntity<PostDto> likePost(@PathVariable(name = "userId") UUID userId, @PathVariable(name = "postId") UUID postId) {
        return postService.likePost(userId, postId);
    }

    @PutMapping(path = "/unlike-post/{userId}/{postId}")
    public ResponseEntity<PostDto> unlikePost(@PathVariable(name = "userId") UUID userId, @PathVariable(name = "postId") UUID postId) {
        return postService.unlikePost(userId, postId);
    }

    @DeleteMapping(path = "/delete/{postId}/{userId}")
    public ResponseEntity<Object> delete(@PathVariable(name = "postId") UUID postId, @PathVariable(name = "userId") UUID userId) {
        return postService.delete(postId, userId);
    }
}
