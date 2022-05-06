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

    /**
     * This method will create an invitation link for the future clients
     * @param transporterId
     * @return the invitation link token
     */
    @PostMapping(path = "/create/{transporterId}")
    public ResponseEntity<InvitationLink> createInvitationLink(@PathVariable(name = "transporterId") UUID transporterId) {
        return invitationLinkService.createInvitationLink(transporterId);
    }

    // Only user with Role TRANSPORTER can access this
    @PutMapping(path = "/update/{transporterId}/{invitationId}")
    public ResponseEntity<InvitationLink> updateInvitationLink(@PathVariable(name = "transporterId") UUID transporterId, @PathVariable(name = "invitationId") UUID invitationId) {
        return invitationLinkService.updateInvitationLink(transporterId, invitationId);
    }

    // Only user with Role TRANSPORTER can access this
    @DeleteMapping(path = "/delete/{invitationLinkId}/{transporterId}")
    public ResponseEntity<Object> deleteInvitationLink(@PathVariable(name = "invitationLinkId") UUID invitationLinkId, @PathVariable(name = "transporterId") UUID transporterId) {
        return invitationLinkService.deleteInvitationLink(invitationLinkId, transporterId);
    }
}
