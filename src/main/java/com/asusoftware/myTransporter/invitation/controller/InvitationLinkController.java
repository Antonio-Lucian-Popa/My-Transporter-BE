package com.asusoftware.myTransporter.invitation.controller;

import com.asusoftware.myTransporter.invitation.model.InvitationLink;
import com.asusoftware.myTransporter.invitation.service.InvitationLinkService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/invitationLink")
@AllArgsConstructor
public class InvitationLinkController {

    private final InvitationLinkService invitationLinkService;

    // Only user with Role TRANSPORTER can access this
    @PostMapping(path = "/createInvitation/{transporterId}")
    public ResponseEntity<InvitationLink> createInvitationLink(@PathVariable(name = "transporterId") UUID transporterId) {
        return invitationLinkService.createInvitationLink(transporterId);
    }

    // Only user with Role TRANSPORTER can access this
    @PutMapping(path = "/createInvitation/{transporterId}/{invitationId}")
    public ResponseEntity<InvitationLink> createInvitationLink(@PathVariable(name = "transporterId") UUID transporterId, @PathVariable(name = "invitationId") UUID invitationId) {
        return invitationLinkService.updateInvitationLink(transporterId, invitationId);
    }
}
