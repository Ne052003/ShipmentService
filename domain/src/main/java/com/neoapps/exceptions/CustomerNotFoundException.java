package com.neoapps.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long customerId) {
        super("A customer with id: " + customerId + " does not exists");
    }
}
