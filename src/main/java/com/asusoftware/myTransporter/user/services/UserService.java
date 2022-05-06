package com.asusoftware.myTransporter.user.services;

import com.asusoftware.myTransporter.address.model.Address;
import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import com.asusoftware.myTransporter.address.services.AddressService;
import com.asusoftware.myTransporter.user.mappers.UserDtoEntity;
import com.asusoftware.myTransporter.user.mappers.UserProfileDtoEntity;
import com.asusoftware.myTransporter.user.model.User;
import com.asusoftware.myTransporter.user.model.UserRole;
import com.asusoftware.myTransporter.user.model.dto.CreateUserDto;
import com.asusoftware.myTransporter.user.model.dto.UserDto;
import com.asusoftware.myTransporter.user.model.dto.UserProfileDto;
import com.asusoftware.myTransporter.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;
 //   private final ImageService imageService;
    private final UserDtoEntity userDtoEntity;
    private final UserProfileDtoEntity userProfileDtoEntity;

    public ResponseEntity<UserDto> create(CreateUserDto createUserDto) {
        User user = userDtoEntity.userToEntity(createUserDto);
        // TODO: Set password with bycrypt
        user.getImage().setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        Address address = addressService.findAddress(createUserDto.getAddressDto());
        user.setAddress(address);
        if(createUserDto.getToken() != null && createUserDto.getUserRole().equals(UserRole.CLIENT)) {
            User transporter = userRepository.findUserByToken(createUserDto.getToken());
            if(transporter != null) {
                user.setFollowed(transporter);
                List<User> followers = transporter.getFollowers();
                followers.add(user);
                transporter.setFollowers(followers);
                userRepository.save(user);
                return ResponseEntity.ok().body(userDtoEntity.userToDto(user));
            }
        } else {
            userRepository.save(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userDtoEntity::userToDto).collect(Collectors.toList());
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<UserDto> findUsersFromAddress(UUID transporterId, AddressDto addressDto) {
        return userRepository.findUsersByAddressId(addressDto.getCountry(), addressDto.getCounty(), addressDto.getCity())
                .stream()
                // Pentru fiecare user gasit vezi daca ii apartine la admin in lista sa de followers
                .filter(user -> user.getFollowed().getId().equals(transporterId))
                .map(userDtoEntity::userToDto).collect(Collectors.toList());
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public UserProfileDto getUserProfile(UUID id) {
        return userProfileDtoEntity.userProfileToDto(userRepository.findById(id).orElse(null));
    }
}
