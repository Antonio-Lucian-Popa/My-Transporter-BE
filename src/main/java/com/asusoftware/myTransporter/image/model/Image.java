package com.asusoftware.myTransporter.image.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "Images")
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}
