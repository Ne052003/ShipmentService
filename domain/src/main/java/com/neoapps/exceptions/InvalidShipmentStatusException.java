package com.neoapps.exceptions;

public class InvalidShipmentStatusException extends RuntimeException {

    public InvalidShipmentStatusException(String newStatus, String requiredStatus) {
        super("The status of the shipment can't be changed to " + newStatus + " because the current status is not " + requiredStatus);
    }
}
