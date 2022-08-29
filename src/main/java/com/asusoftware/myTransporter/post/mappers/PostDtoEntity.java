package com.asusoftware.myTransporter.post.mappers;

import com.asusoftware.myTransporter.address.mappers.AddressDtoEntity;
import com.asusoftware.myTransporter.image.mappers.ImageDtoEntity;
import com.asusoftware.myTransporter.post.model.Post;
import com.asusoftware.myTransporter.post.model.dto.CreatePostDto;
import com.asusoftware.myTransporter.post.model.dto.PostDto;
import com.asusoftware.myTransporter.user.mappers.UserDtoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {AddressDtoEntity.class, ImageDtoEntity.class, UserDtoEntity.class, LikeDtoEntity.class}, componentModel = "spring")
public interface PostDtoEntity {

    @Mappings({
            @Mapping(source = "createPostDto.addressDto", target = "address"),
            @Mapping(source = "createPostDto.imageDto", target = "image"),
    })
    Post createPostDtoToEntity(CreatePostDto createPostDto);

    @Mappings({
            @Mapping(source = "post.address", target = "addressDto"),
            @Mapping(source = "post.image", target = "imageDto"),
            @Mapping(source = "post.user", target = "userDto"),
            @Mapping(source = "post.usersLike", target = "likes"),
    })
    PostDto postEntityToDto(Post post);
}
