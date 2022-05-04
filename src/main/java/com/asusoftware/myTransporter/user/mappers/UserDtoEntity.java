package com.asusoftware.myTransporter.user.mappers;

import com.asusoftware.myTransporter.address.mappers.AddressDtoEntity;
import com.asusoftware.myTransporter.user.model.User;
import com.asusoftware.myTransporter.user.model.dto.CreateUserDto;
import com.asusoftware.myTransporter.user.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {AddressDtoEntity.class}, componentModel = "spring")
public interface UserDtoEntity {

    @Mappings({
            @Mapping(source = "user.address", target = "addressDto"),
            @Mapping(source = "user.image", target = "image"),
    })
    UserDto userToDto(User user);

    @Mappings({
            @Mapping(source = "createUserDto.addressDto", target = "address"),
            @Mapping(source = "createUserDto.image", target = "image"),
    })
    User userToEntity(CreateUserDto createUserDto);
}
