package com.asusoftware.myTransporter.address.model;


import com.asusoftware.myTransporter.user.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "Address")
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "county", nullable = false)
    private String county;

    @Column(name = "city", nullable = false)
    private String city;

    @OneToMany(mappedBy = "address")
    private List<User> user;
}
