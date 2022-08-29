package com.asusoftware.myTransporter.user.model;


import com.asusoftware.myTransporter.address.model.Address;
import com.asusoftware.myTransporter.image.model.Image;
import com.asusoftware.myTransporter.invitation.model.InvitationLink;
import com.asusoftware.myTransporter.notification.model.NotificationAction;
import com.asusoftware.myTransporter.post.model.Likes;
import com.asusoftware.myTransporter.post.model.Post;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "Users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthday", nullable = false, columnDefinition = "DATE")
    private Date birthday;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    // Un user puo avere solo un'address
    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST })
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy="user")
    private List<Post> posts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    // Un user poatea avea mai multi urmaritori
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "followers_id", referencedColumnName = "id")
    public List<User> followers;

    // Mai multi useri au doar un urmarit
    @ManyToOne
    @JoinColumn(name = "followed_id", referencedColumnName = "id")
    public User followed;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<NotificationAction> notificationAction;

    // Only user that have role as TRANSPORTER can have an invitation link
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invitation_link_id", referencedColumnName = "id")
    private InvitationLink invitationLink;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="post_id")
    private Post post;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Likes> postsLike;

}

// Cascade Type PERSIST(save) propagates the persist operation from a parent to a child entity. When we save the person entity, the address entity will also get saved.
// When we use CascadeType.DETACH(remove child from persistent context), the child entity will also get removed from the persistent context.
// When we use this operation with Cascade Type REFRESH, the child entity also gets reloaded from the database whenever the parent entity is refreshed.
