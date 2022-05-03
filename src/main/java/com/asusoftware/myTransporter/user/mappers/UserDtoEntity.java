package com.asusoftware.myTransporter.user.mappers;

import com.asusoftware.myTransporter.address.mappers.AddressDtoEntity;
import com.asusoftware.myTransporter.user.model.User;
import com.asusoftware.myTransporter.user.model.dto.CreateUserDto;
import com.asusoftware.myTransporter.user.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {AddressDtoEntity.class}, componentModel = "spring")
public interface UserDtoEntity {

    UserDto userToDto(User user);

    @Mapping(source = "createUserDto.addressDto", target = "address")
    User userToEntity(CreateUserDto createUserDto);
}
