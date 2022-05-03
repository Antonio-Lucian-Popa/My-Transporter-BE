package com.asusoftware.myTransporter.user.mappers;

import com.asusoftware.myTransporter.user.model.User;
import com.asusoftware.myTransporter.user.model.dto.UserProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {FollowersDtoEntity.class}, componentModel = "spring")
public interface UserProfileDtoEntity {

    @Mapping(source = "user.followers", target = "followersDtos")
    UserProfileDto userProfileToDto(User user);
}