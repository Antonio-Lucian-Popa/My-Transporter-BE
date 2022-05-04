package com.asusoftware.myTransporter.invitation.repository;

import com.asusoftware.myTransporter.invitation.model.InvitationLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvitationLinkRepository extends JpaRepository<InvitationLink, UUID> {
}
