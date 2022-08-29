package com.asusoftware.myTransporter.post.model.dto;

import com.asusoftware.myTransporter.user.model.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LikesDto {
    private UserDto user;
    private LocalDateTime createdAt;
}
