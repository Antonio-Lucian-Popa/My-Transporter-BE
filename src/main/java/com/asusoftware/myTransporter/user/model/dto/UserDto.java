package com.asusoftware.myTransporter.user.model.dto;

import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import com.asusoftware.myTransporter.image.model.dto.ImageDto;
import com.asusoftware.myTransporter.user.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private UUID id;
    private ImageDto image;
    private String firstName;
    private String lastName;
    private AddressDto addressDto;
    private UserRole userRole;
}