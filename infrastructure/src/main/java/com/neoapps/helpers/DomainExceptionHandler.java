package com.neoapps.helpers;

import com.neoapps.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DomainExceptionHandler {

    @ExceptionHandler(InvalidShipmentStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidShipmentStatus(InvalidShipmentStatusException ex) {
        ErrorResponse errorResponse = new ErrorResponse("INVALID_SHIPMENT_STATUS", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(CustomerNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("CUSTOMER_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFound(OrderNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("ORDER_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(ShipmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleShipmentNotFound(ShipmentNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("SHIPMENT_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException ex) {
        ErrorResponse errorResponse = new ErrorResponse("DOMAIN_ERROR", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<ErrorResponse> handleExternalServiceException(ExternalServiceException ex) {
        ErrorResponse errorResponse = new ErrorResponse("EXTERNAL_SERVICE_ERROR", ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidInputException ex) {
        ErrorResponse errorResponse = new ErrorResponse("INVALID_INPUT", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
