package com.asusoftware.myTransporter.post.services;

import com.asusoftware.myTransporter.address.model.Address;
import com.asusoftware.myTransporter.address.services.AddressService;
import com.asusoftware.myTransporter.exceptions.PostNotFoundException;
import com.asusoftware.myTransporter.post.mappers.PostDtoEntity;
import com.asusoftware.myTransporter.post.model.Post;
import com.asusoftware.myTransporter.post.model.dto.CreatePostDto;
import com.asusoftware.myTransporter.post.model.dto.PostDto;
import com.asusoftware.myTransporter.post.repository.PostRepository;
import com.asusoftware.myTransporter.user.model.User;
import com.asusoftware.myTransporter.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostDtoEntity postDtoEntity;
    private final UserService userService;
    private final AddressService addressService;


    /**
     * Only user with role transporter can create post
     * @param userId will be the id of the transporter
     * @param createPostDto will be the post data from client
     */
    public void create(UUID userId, CreatePostDto createPostDto) {
        User user = userService.findById(userId);
        Post post = postDtoEntity.createPostDtoToEntity(createPostDto);
        Address address = addressService.findAddress(createPostDto.getAddressDto());
        List<Post> posts = user.getPosts();
        posts.add(post);
        user.setPosts(posts);
        post.getImage().setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        post.setUser(user);
        post.setAddress(address);
        post.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        postRepository.save(post);
    }

    public ResponseEntity<List<PostDto>> findAllPostsByPage(UUID userId, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 10, Sort.by("createdAt").ascending());
        List<PostDto> posts = postRepository.findAllPostsByPage(userId, pageRequest).stream().map(postDtoEntity::postEntityToDto).collect(Collectors.toList());
        return ResponseEntity.ok().body(posts);
    }

    public ResponseEntity<List<PostDto>> findAll() {
        List<PostDto> postDtoList = postRepository.findAll().stream().map(postDtoEntity::postEntityToDto).collect(Collectors.toList());
        return ResponseEntity.ok(postDtoList);
    }

    /**
     * Only the transporter can delete a post
     * @param postId will be the identifier of the post that you want to delete
     * @param userId will be the owner of the post that you want to delete
     * @return response with the current status
     */
    public ResponseEntity<Object> delete(UUID postId, UUID userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(String.format("The post with the id: %s was not found!", postId)));
        if (post.getUser().getId().equals(userId)) {
            postRepository.deleteById(postId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Is not possible to delete this post, because you don't have the permission!");
        }
    }

    public ResponseEntity<PostDto> likePost(UUID userId, UUID postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(String.format("The post with the id: %s was not found!", postId)));
        User user = userService.findById(userId);
        List<User> likes = post.getLikes();
        likes.add(user);
        post.setLikes(likes);
        postRepository.save(post);
        return ResponseEntity.ok(postDtoEntity.postEntityToDto(post));
    }

    public ResponseEntity<PostDto> unlikePost(UUID userId, UUID postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(String.format("The post with the id: %s was not found!", postId)));
        User user = userService.findById(userId);
        List<User> likes = post.getLikes();
        likes.remove(user);
        post.setLikes(likes);
        postRepository.save(post);
        return ResponseEntity.ok(postDtoEntity.postEntityToDto(post));
    }
}
