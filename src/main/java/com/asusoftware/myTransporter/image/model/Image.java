package com.asusoftware.myTransporter.image.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "Images")
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue
    private UUID id;

    private String value;


}
