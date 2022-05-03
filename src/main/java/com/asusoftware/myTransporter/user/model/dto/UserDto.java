package com.asusoftware.myTransporter.user.model.dto;

import com.asusoftware.myTransporter.user.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private UserRole userRole;
}