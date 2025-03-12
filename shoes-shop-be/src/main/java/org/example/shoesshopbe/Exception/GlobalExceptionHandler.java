package org.example.shoesshopbe.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(e.messageError.getMessage(), e.httpStatus);
    }
    @ExceptionHandler(Exception.class)
     public ResponseEntity<MessageError> handleGenericException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(MessageError.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
