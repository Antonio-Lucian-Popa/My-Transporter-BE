package com.asusoftware.myTransporter.user.model.dto;

import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import com.asusoftware.myTransporter.user.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FollowersDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private AddressDto addressDto;
    //private String email;
    private String phoneNumber;
    //private LocalDate birthday;
    private UserRole userRole;
}