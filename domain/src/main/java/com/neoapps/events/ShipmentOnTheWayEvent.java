package com.neoapps.events;

import java.time.LocalDateTime;

public record ShipmentOnTheWayEvent (Long shipmentId, LocalDateTime occurredAt) {
}
