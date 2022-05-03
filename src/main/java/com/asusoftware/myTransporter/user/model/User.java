package com.asusoftware.myTransporter.user.model;


import com.asusoftware.myTransporter.address.model.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
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

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    // Un user puo avere solo un'address
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
