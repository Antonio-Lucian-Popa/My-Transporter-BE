package com.asusoftware.myTransporter.image.services;

import com.asusoftware.myTransporter.image.mappers.ImageDtoEntity;
import com.asusoftware.myTransporter.image.model.Image;
import com.asusoftware.myTransporter.image.model.dto.ImageDto;
import com.asusoftware.myTransporter.image.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageDtoEntity imageDtoEntity;

    public Image create(ImageDto imageDto){
        Image image = imageDtoEntity.imageDtoToEntity(imageDto);
        image.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        return imageRepository.save(image);
    }
}
