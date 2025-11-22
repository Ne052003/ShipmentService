package com.neoapps.events;

import java.time.LocalDateTime;

public record ShipmentPendingEvent(Long shipmentId, LocalDateTime occurredAt) {

}
