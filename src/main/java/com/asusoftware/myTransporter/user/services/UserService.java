package com.asusoftware.myTransporter.user.services;

import com.asusoftware.myTransporter.user.mappers.UserDtoEntity;
import com.asusoftware.myTransporter.user.model.User;
import com.asusoftware.myTransporter.user.model.dto.CreateUserDto;
import com.asusoftware.myTransporter.user.model.dto.UserDto;
import com.asusoftware.myTransporter.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoEntity userDtoEntity;

    public void create(CreateUserDto createUserDto) {
        User user = userDtoEntity.userToEntity(createUserDto);
        userRepository.save(user);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userDtoEntity::userToDto).collect(Collectors.toList());
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

}
