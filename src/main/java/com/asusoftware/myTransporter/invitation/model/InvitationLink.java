package com.asusoftware.myTransporter.invitation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "InvitationLinks")
@Table(name = "invitation_links")
public class InvitationLink {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expired_at", nullable = false)
    private LocalDateTime expiredAt;
}
