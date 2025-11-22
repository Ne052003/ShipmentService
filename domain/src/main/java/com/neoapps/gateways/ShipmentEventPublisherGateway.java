package com.neoapps.gateways;

import com.neoapps.events.ShipmentDeliveredEvent;
import com.neoapps.events.ShipmentOnTheWayEvent;
import com.neoapps.events.ShipmentPendingEvent;
import reactor.core.publisher.Mono;

public interface ShipmentEventPublisherGateway {

    Mono<Void> publishShipmentPendingEvent(ShipmentPendingEvent event);

    Mono<Void> publishShipmentOnTheWayEvent(ShipmentOnTheWayEvent event);

    Mono<Void> publishShipmentDeliveredEvent(ShipmentDeliveredEvent event);
}
