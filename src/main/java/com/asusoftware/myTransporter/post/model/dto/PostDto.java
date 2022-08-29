package com.asusoftware.myTransporter.post.model.dto;

import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import com.asusoftware.myTransporter.image.model.dto.ImageDto;
import com.asusoftware.myTransporter.post.model.Likes;
import com.asusoftware.myTransporter.user.model.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class PostDto {

    private UUID id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private AddressDto addressDto;
    private ImageDto imageDto;
    // The owner of the post
    private UserDto userDto;
    // User that likes this post
    private List<LikesDto> likes;
}
