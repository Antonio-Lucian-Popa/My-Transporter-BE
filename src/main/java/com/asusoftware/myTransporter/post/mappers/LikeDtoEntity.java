package com.asusoftware.myTransporter.post.mappers;

import com.asusoftware.myTransporter.post.model.Likes;
import com.asusoftware.myTransporter.post.model.Post;
import com.asusoftware.myTransporter.post.model.dto.LikesDto;
import com.asusoftware.myTransporter.post.model.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface LikeDtoEntity {

    @Mappings({
            @Mapping(source = "post.user", target = "user"),
            @Mapping(source = "post.createdAt", target = "createdAt"),
    })
    LikesDto likeEntityToDto(Likes likes);
}
