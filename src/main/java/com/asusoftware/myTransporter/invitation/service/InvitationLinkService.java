package com.asusoftware.myTransporter.invitation.service;

import com.asusoftware.myTransporter.invitation.model.InvitationLink;
import com.asusoftware.myTransporter.invitation.repository.InvitationLinkRepository;
import com.asusoftware.myTransporter.user.model.User;
import com.asusoftware.myTransporter.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvitationLinkService {

    private final InvitationLinkRepository invitationLinkRepository;
    private final UserService userService;

    public ResponseEntity<InvitationLink> createInvitationLink(UUID transporterId) {
        User user = userService.findById(transporterId);
        if(user != null) {
            InvitationLink invitationLink = generateInvitationLink();
            user.setInvitationLink(invitationLink);
            userService.saveUser(user);
            return ResponseEntity.ok().body(invitationLink);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<InvitationLink> updateInvitationLink(UUID transporterId, UUID invitationId) {
        User user = userService.findById(transporterId);
        if(user != null) {
            InvitationLink invitationLink = generateUpdateInvitationLink(invitationId);
            if(invitationLink != null) {
                user.setInvitationLink(invitationLink);
                userService.saveUser(user);
                return ResponseEntity.ok().body(invitationLink);
            }
        }
        return ResponseEntity.notFound().build();
    }

    public InvitationLink generateInvitationLink() {
        InvitationLink invitationLink = new InvitationLink();
        invitationLink.setToken(UUID.randomUUID().toString());
        invitationLink.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        invitationLink.setExpiredAt(LocalDateTime.now(ZoneOffset.UTC).plusDays(5));
        invitationLinkRepository.save(invitationLink);
        return invitationLink;
    }

    public InvitationLink generateUpdateInvitationLink(UUID invitationId) {
        InvitationLink invitationLink = invitationLinkRepository.findById(invitationId).orElse(null);
        invitationLink.setToken(UUID.randomUUID().toString());
        invitationLink.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        invitationLink.setExpiredAt(LocalDateTime.now(ZoneOffset.UTC).plusDays(5));
        invitationLinkRepository.save(invitationLink);
        return invitationLink;
    }
}
