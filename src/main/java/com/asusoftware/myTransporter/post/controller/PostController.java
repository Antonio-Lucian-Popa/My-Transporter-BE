package com.asusoftware.myTransporter.post.controller;

import com.asusoftware.myTransporter.post.model.dto.CreatePostDto;
import com.asusoftware.myTransporter.post.model.dto.PostDto;
import com.asusoftware.myTransporter.post.services.PostService;
import com.asusoftware.myTransporter.user.model.dto.UserDto;
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

    @DeleteMapping(path = "/delete/{postId}/{userId}")
    public ResponseEntity<Object> delete(@PathVariable(name = "postId") UUID postId, @PathVariable(name = "userId") UUID userId) {
        return postService.delete(postId, userId);
    }
}
