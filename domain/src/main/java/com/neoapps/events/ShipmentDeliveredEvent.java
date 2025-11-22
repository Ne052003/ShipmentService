package com.neoapps.events;

import java.time.LocalDateTime;

public record ShipmentDeliveredEvent(Long shipmentId, LocalDateTime occurredAt) {
}
