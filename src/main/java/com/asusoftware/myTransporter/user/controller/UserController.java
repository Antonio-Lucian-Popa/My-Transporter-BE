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


    /**
     * Thhis method will create the actual user(TRANSPORTER or CLIENT)
     * @param createUserDto the actual user information to store in the db
     * @return
     */
    @PostMapping(path = "/create")
    public ResponseEntity<UserDto> create(@RequestBody CreateUserDto createUserDto) {
        return userService.create(createUserDto);
    }

    /**
     * This method will return a user profile
     * @param id is the identifier for the user that we need
     * @return the current user that want to view information in more detail
     */
    @GetMapping(path = "/userProfile/{id}")
    public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable(name = "id") UUID id) {
        return userService.getUserProfile(id);
    }

    @GetMapping(path = "/findAll")
    public List<UserDto> findAll() {
       return userService.findAll();
    }

    /**
     * This method will return all clients from the specified address filter
     * @param transporterId where the followers will be searched
     * @param addressDto this is the address filter with the data to filter clients
     * @return a list of clients that have the filter criteria
     */
    @GetMapping(path = "/findAllFromAddress/{transporterId}")
    public List<UserDto> findUsersFromAddress(@PathVariable(name = "transporterId") UUID transporterId, @RequestBody AddressDto addressDto) {
        return userService.findUsersFromAddress(transporterId, addressDto);
    }

    /**
     * Only for transporter
     * @param id this will be the transporter id, to find the actual followers
     * @param pageNumber we need thi to load sequentially followers
     * @return A list of 10 followers per page request
     */
    @GetMapping(path = "/findAllTransporterFollowers/{id}/{pageNumber}")
    public ResponseEntity<List<UserDto>> findAllTransporterFollowers(@PathVariable(name = "id") UUID id, @PathVariable(name = "pageNumber") int pageNumber) {
        return userService.findAllTransporterFollowers(id, pageNumber);
    }

    /**
     * This method will delete the user using his id
     * @param id of the user to delete
     * @return a response with the current situation
     */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") UUID id) {
        return userService.delete(id);
    }

    /**
     * This method will remove the specified follower from the transporter client list
     * @param followerId the user to remove from the client list
     * @param transporterId the user where the client should be removed
     */
    @DeleteMapping(path = "/removeFollower/{followerId}/{transporterId}")
    public void removeFollowerById(@PathVariable(name = "followerId") UUID followerId, @PathVariable(name = "transporterId") UUID transporterId) {
        userService.removeFollowerById(followerId, transporterId);
    }

}
