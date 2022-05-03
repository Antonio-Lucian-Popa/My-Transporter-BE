package com.asusoftware.myTransporter.user.mappers;

import com.asusoftware.myTransporter.user.model.User;
import com.asusoftware.myTransporter.user.model.dto.FollowersDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FollowersDtoEntity {
    FollowersDto userToDto(User user);
}