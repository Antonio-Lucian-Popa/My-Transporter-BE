package com.asusoftware.myTransporter.user.model.dto;

import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import com.asusoftware.myTransporter.user.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserProfileDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private AddressDto addressDto;
    private String email;
    private String phoneNumber;
    private LocalDate birthday;
    private UserRole userRole;
    // urmarit
    private UserDto followed;
    // urmaritori
    private List<FollowersDto> followersDtos;
}