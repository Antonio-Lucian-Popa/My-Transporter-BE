package com.asusoftware.myTransporter.invitation.service;

import com.asusoftware.myTransporter.invitation.model.InvitationLink;
import com.asusoftware.myTransporter.invitation.repository.InvitationLinkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvitationLinkService {

    private final InvitationLinkRepository invitationLinkRepository;

    public InvitationLink createInvitationLink() {
        InvitationLink invitationLink = new InvitationLink();
        invitationLink.setToken(UUID.randomUUID().toString());
        invitationLink.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        invitationLink.setExpiredAt(LocalDateTime.now(ZoneOffset.UTC).plusDays(5));
        invitationLinkRepository.save(invitationLink);
        return invitationLink;
    }

    public InvitationLink updateInvitationLink(UUID invitationId) {
        InvitationLink invitationLink = invitationLinkRepository.findById(invitationId).orElse(null);
        invitationLink.setToken(UUID.randomUUID().toString());
        invitationLink.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        invitationLink.setExpiredAt(LocalDateTime.now(ZoneOffset.UTC).plusDays(5));
        invitationLinkRepository.save(invitationLink);
        return invitationLink;
    }
}
