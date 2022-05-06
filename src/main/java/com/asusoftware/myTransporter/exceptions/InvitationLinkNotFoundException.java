package com.asusoftware.myTransporter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvitationLinkNotFoundException extends RuntimeException {

    public InvitationLinkNotFoundException(String message) {
        super(message);
    }
}
