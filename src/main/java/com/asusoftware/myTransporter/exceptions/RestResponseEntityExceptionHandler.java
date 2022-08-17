package com.asusoftware.myTransporter.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

   /* @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
        return new ResponseEntity<String>(
                "You don't have the right access", HttpStatus.FORBIDDEN);
    } */

  /*  @ExceptionHandler(value = {SignatureException.class})
    @ResponseStatus(HttpStatus.CONFLICT) // 409
    public String handleSignatureException() {
        return "JWT signature does not match";
    } */

  /*  @ExceptionHandler(value = {ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.CONFLICT) // 409
    public String handleExpiredJwtException() {
        return "JWT expired";
    } */

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException e, WebRequest webRequest){
        System.out.println("Error: " + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Jwt expired");
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
