package com.asusoftware.myTransporter.user.services;

import com.asusoftware.myTransporter.address.model.Address;
import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import com.asusoftware.myTransporter.address.services.AddressService;
import com.asusoftware.myTransporter.exceptions.UserNotFoundException;
import com.asusoftware.myTransporter.user.mappers.UserDtoEntity;
import com.asusoftware.myTransporter.user.mappers.UserProfileDtoEntity;
import com.asusoftware.myTransporter.user.model.User;
import com.asusoftware.myTransporter.user.model.UserRole;
import com.asusoftware.myTransporter.user.model.dto.CreateUserDto;
import com.asusoftware.myTransporter.user.model.dto.UserDto;
import com.asusoftware.myTransporter.user.model.dto.UserProfileDto;
import com.asusoftware.myTransporter.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
            User transporter = userRepository.findUserByToken(createUserDto.getToken())
                    .orElseThrow(() -> new UserNotFoundException(String.format("This token: %s are not valid", createUserDto.getToken())));
                user.setFollowed(transporter);
                List<User> followers = transporter.getFollowers();
                followers.add(user);
                transporter.setFollowers(followers);
                userRepository.save(user);
                return ResponseEntity.ok().body(userDtoEntity.userToDto(user));
        } else {
            userRepository.save(user);
            return ResponseEntity.ok().build();
        }
       // return ResponseEntity.badRequest().build();
    }


    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userDtoEntity::userToDto).collect(Collectors.toList());
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("The user with Id: %s, not found", id)));
    }

    public ResponseEntity<List<UserDto>> findAllTransporterFollowers(UUID transporterId, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 10, Sort.by("firstName").ascending());
        List<UserDto> followers = userRepository.findTransporterFollowersWithPagination(transporterId, pageRequest).stream().map(userDtoEntity::userToDto).collect(Collectors.toList());
        return ResponseEntity.ok().body(followers);
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

    /**
     * Only for transporter
     */
    public ResponseEntity<String> removeFollowerById(UUID followerId, UUID transporterId) {
        // Find the transporter
        User transporter = findById(transporterId);
        if(transporter != null) {
            List<User> clients = transporter.getFollowers();
            // Remove the client from client list of the transporter
            clients.removeIf(user -> user.getId().equals(followerId));
            transporter.setFollowers(clients);
            saveUser(transporter);
            userRepository.deleteById(followerId);
            return ResponseEntity.ok().body("Size: " + clients.size());
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<String> delete(UUID id) {
        User user = findById(id);
        if(user.getUserRole().equals(UserRole.CLIENT)) {
           return removeFollowerById(id, user.getFollowed().getId());
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<UserProfileDto> getUserProfile(UUID id) {
        UserProfileDto userProfileDto = userProfileDtoEntity.userProfileToDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("The actual user, not found")));
        return userProfileDto != null ? ResponseEntity.ok().body(userProfileDto) : ResponseEntity.notFound().build();
    }
}
