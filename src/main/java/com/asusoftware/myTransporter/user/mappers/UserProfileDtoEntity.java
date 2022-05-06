package com.asusoftware.myTransporter.user.mappers;

import com.asusoftware.myTransporter.user.model.User;
import com.asusoftware.myTransporter.user.model.dto.UserProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {FollowersDtoEntity.class, UserDtoEntity.class}, componentModel = "spring")
public interface UserProfileDtoEntity {

    @Mappings({
            @Mapping(source = "user.address", target = "addressDto"),
            @Mapping(source = "user.followed", target = "followed"),
            @Mapping(source = "user.followers", target = "followersDtos")
    })
    UserProfileDto userProfileToDto(User user);
}