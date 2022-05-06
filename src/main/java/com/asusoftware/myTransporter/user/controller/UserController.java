package com.asusoftware.myTransporter.user.controller;

import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import com.asusoftware.myTransporter.user.model.dto.CreateUserDto;
import com.asusoftware.myTransporter.user.model.dto.UserDto;
import com.asusoftware.myTransporter.user.model.dto.UserProfileDto;
import com.asusoftware.myTransporter.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/create")
    public ResponseEntity<UserDto> create(@RequestBody CreateUserDto createUserDto) {
        return userService.create(createUserDto);
    }

    @GetMapping(path = "/userProfile/{id}")
    public UserProfileDto getUserProfile(@PathVariable(name = "id") UUID id) {
        return userService.getUserProfile(id);
    }

    @GetMapping(path = "/findAll")
    public List<UserDto> findAll() {
       return userService.findAll();
    }

    @GetMapping(path = "/findAllFromAddress/{transporterId}")
    public List<UserDto> findUsersFromAddress(@PathVariable(name = "transporterId") UUID transporterId, @RequestBody AddressDto addressDto) {
        return userService.findUsersFromAddress(transporterId, addressDto);
    }

    @DeleteMapping(path = "/delete")
    public void delete(UUID id) {
        userService.delete(id);
    }

}
