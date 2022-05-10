package com.asusoftware.myTransporter.post.model.dto;

import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import com.asusoftware.myTransporter.image.model.dto.ImageDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreatePostDto {

    private String title;
    private String description;
    private AddressDto addressDto;
    private ImageDto imageDto;
}
