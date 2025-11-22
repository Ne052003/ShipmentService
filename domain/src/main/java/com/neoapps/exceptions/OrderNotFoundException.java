package com.neoapps.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long orderId) {
        super("An order with id: " + orderId + " does not exists");
    }
}
