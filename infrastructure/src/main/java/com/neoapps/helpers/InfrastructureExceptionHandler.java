package com.neoapps.helpers;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InfrastructureExceptionHandler {

    @ExceptionHandler(MessageDeliveryException.class)
    public ResponseEntity<ErrorResponse> handleSNSDeliveryException(MessageDeliveryException ex) {
        ErrorResponse errorResponse = new ErrorResponse("ERROR_SENDING_MESSAGE_TO_SNS", ex.getCause().getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }

}
