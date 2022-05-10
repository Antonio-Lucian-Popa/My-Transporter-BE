package com.asusoftware.myTransporter.post.model.dto;

import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import com.asusoftware.myTransporter.image.model.dto.ImageDto;
import com.asusoftware.myTransporter.user.model.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class PostDto {

    private UUID id;
    private String title;
    private String description;
    private AddressDto addressDto;
    private ImageDto imageDto;
    private UserDto userDto;
}
