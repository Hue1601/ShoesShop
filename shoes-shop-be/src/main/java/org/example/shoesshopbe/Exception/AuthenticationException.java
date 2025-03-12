package org.example.shoesshopbe.Exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends RuntimeException {
    public final MessageError messageError;
    public final HttpStatus httpStatus;

    public AuthenticationException(MessageError messageError, HttpStatus httpStatus) {
        super(messageError.getMessage());
        this.messageError = messageError;
        this.httpStatus = httpStatus;
    }
}
