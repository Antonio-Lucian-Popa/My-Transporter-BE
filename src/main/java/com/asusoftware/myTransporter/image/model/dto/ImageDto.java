package com.asusoftware.myTransporter.image.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ImageDto {

    private UUID id;
    private String value;
    private LocalDateTime createdAt;
}
