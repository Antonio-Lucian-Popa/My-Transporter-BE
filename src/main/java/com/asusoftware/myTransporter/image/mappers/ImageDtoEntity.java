package com.asusoftware.myTransporter.image.mappers;

import com.asusoftware.myTransporter.image.model.Image;
import com.asusoftware.myTransporter.image.model.dto.ImageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageDtoEntity {

    ImageDto imageToDto(Image image);
    Image imageDtoToEntity(ImageDto imageDto);
}
