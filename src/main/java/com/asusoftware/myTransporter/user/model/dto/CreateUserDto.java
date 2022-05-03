package com.asusoftware.myTransporter.user.model.dto;

import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import com.asusoftware.myTransporter.user.model.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CreateUserDto {

    private String firstName;
    private String lastName;
    private AddressDto addressDto;
    private String email;
    private String phoneNumber;
    private String password;
    private LocalDate birthday;
    private UserRole userRole;
}