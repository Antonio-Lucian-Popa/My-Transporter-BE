package com.asusoftware.myTransporter.user.mappers;

import com.asusoftware.myTransporter.address.mappers.AddressDtoEntity;
import com.asusoftware.myTransporter.user.model.User;
import com.asusoftware.myTransporter.user.model.dto.FollowersDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {AddressDtoEntity.class}, componentModel = "spring")
public interface FollowersDtoEntity {

    @Mappings({
            @Mapping(source = "user.address", target = "addressDto"),
    })
    FollowersDto userToDto(User user);
}