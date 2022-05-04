package com.asusoftware.myTransporter.user.services;

import com.asusoftware.myTransporter.address.model.Address;
import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import com.asusoftware.myTransporter.address.services.AddressService;
import com.asusoftware.myTransporter.image.model.Image;
import com.asusoftware.myTransporter.image.services.ImageService;
import com.asusoftware.myTransporter.invitation.model.InvitationLink;
import com.asusoftware.myTransporter.invitation.service.InvitationLinkService;
import com.asusoftware.myTransporter.user.mappers.UserDtoEntity;
import com.asusoftware.myTransporter.user.model.User;
import com.asusoftware.myTransporter.user.model.dto.CreateUserDto;
import com.asusoftware.myTransporter.user.model.dto.UserDto;
import com.asusoftware.myTransporter.user.repository.UserRepository;
import lombok.AllArgsConstructor;
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
    private final InvitationLinkService invitationLinkService;
 //   private final ImageService imageService;
    private final UserDtoEntity userDtoEntity;

    public void create(CreateUserDto createUserDto) {
        User user = userDtoEntity.userToEntity(createUserDto);
     //   Image image = imageService.create(createUserDto.getImage());
        Address address = addressService.findAddress(createUserDto.getAddressDto());
        user.getImage().setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
     //   user.setImage(image);
        user.setAddress(address);
        userRepository.save(user);
    }

    public void createInvitationLink(UUID transporterId) {
        User user = userRepository.findById(transporterId).orElse(null);
        if(user != null) {
            InvitationLink invitationLink = invitationLinkService.createInvitationLink();
            user.setInvitationLink(invitationLink);
            userRepository.save(user);
            System.out.println(invitationLink);
        }
    }

    public void updateInvitationLink(UUID transporterId, UUID invitationId) {
        User user = userRepository.findById(transporterId).orElse(null);
        if(user != null) {
            InvitationLink invitationLink = invitationLinkService.updateInvitationLink(invitationId);
            user.setInvitationLink(invitationLink);
            userRepository.save(user);
            System.out.println(invitationLink);
        }
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userDtoEntity::userToDto).collect(Collectors.toList());
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

}
