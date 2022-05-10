package com.asusoftware.myTransporter.post.model;

import com.asusoftware.myTransporter.address.model.Address;
import com.asusoftware.myTransporter.image.model.Image;
import com.asusoftware.myTransporter.user.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "Posts")
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Piu post puo avere solo un'address
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    // Piu post puo avere solo un'address
    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

}
