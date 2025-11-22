package com.neoapps.exceptions;

public class ShipmentNotFoundException extends RuntimeException {

    public ShipmentNotFoundException(Long shipmentId) {
        super("The shipment with id: " + shipmentId + " was not found");
    }
}
