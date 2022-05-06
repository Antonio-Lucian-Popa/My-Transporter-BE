package com.asusoftware.myTransporter.user.model.dto;

import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import com.asusoftware.myTransporter.image.model.dto.ImageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class UpdateUserDto {

    private UUID id;
    private ImageDto image;
    private String firstName;
    private String lastName;
    private AddressDto addressDto;
    private String email;
    private String phoneNumber;
    private Date birthday;
}
