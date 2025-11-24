package com.neoapps.usecases;

import com.neoapps.events.ShipmentDeliveredEvent;
import com.neoapps.events.ShipmentOnTheWayEvent;
import com.neoapps.events.ShipmentPendingEvent;
import com.neoapps.exceptions.InvalidShipmentStatusException;
import com.neoapps.exceptions.ShipmentNotFoundException;
import com.neoapps.gateways.ShipmentEventPublisherGateway;
import com.neoapps.gateways.ShipmentRepositoryGateway;
import com.neoapps.model.ShipmentStatus;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class UpdateShipmentUseCase {

    private final ShipmentRepositoryGateway shipmentRepositoryGateway;
    private final ShipmentEventPublisherGateway shipmentEventPublisher;

    public UpdateShipmentUseCase(ShipmentRepositoryGateway shipmentRepositoryGateway, ShipmentEventPublisherGateway shipmentEventPublisher) {
        this.shipmentRepositoryGateway = shipmentRepositoryGateway;
        this.shipmentEventPublisher = shipmentEventPublisher;
    }

    public Mono<Void> setShipmentStatusToPendingShipping(Long id) {
        return shipmentRepositoryGateway.getById(id)
                .switchIfEmpty(Mono.error(new ShipmentNotFoundException(id)))
                .flatMap(shipment -> {
                    if (!shipment.getStatus().equals(ShipmentStatus.RECEIVED)) {
                        return Mono.error(new InvalidShipmentStatusException(ShipmentStatus.PENDING_SHIPPING.name(), ShipmentStatus.RECEIVED.name()));
                    }
                    shipment.setStatus(ShipmentStatus.PENDING_SHIPPING);
                    return shipmentRepositoryGateway.save(shipment)
                            .then(shipmentEventPublisher.publishShipmentPendingEvent(new ShipmentPendingEvent(shipment.getId(), LocalDateTime.now())));
                });
    }

    public Mono<Void> setShipmentStatusToOnTheWay(Long id) {
        return shipmentRepositoryGateway.getById(id)
                .switchIfEmpty(Mono.error(new ShipmentNotFoundException(id)))
                .flatMap(shipment -> {
                    if (!shipment.getStatus().equals(ShipmentStatus.PENDING_SHIPPING)) {
                        return Mono.error(new InvalidShipmentStatusException(ShipmentStatus.ON_THE_WAY.name(), ShipmentStatus.PENDING_SHIPPING.name()));
                    }
                    shipment.setStatus(ShipmentStatus.ON_THE_WAY);
                    return shipmentRepositoryGateway.save(shipment)
                            .then(shipmentEventPublisher.publishShipmentOnTheWayEvent(new ShipmentOnTheWayEvent(shipment.getId(), LocalDateTime.now())));
                });
    }

    public Mono<Void> setShipmentStatusToDelivered(Long id) {
        return shipmentRepositoryGateway.getById(id)
                .switchIfEmpty(Mono.error(new ShipmentNotFoundException(id)))
                .flatMap(shipment -> {
                    if (!shipment.getStatus().equals(ShipmentStatus.ON_THE_WAY)) {
                        return Mono.error(new InvalidShipmentStatusException(ShipmentStatus.DELIVERED.name(), ShipmentStatus.ON_THE_WAY.name()));
                    }
                    shipment.setStatus(ShipmentStatus.DELIVERED);
                    return shipmentRepositoryGateway.save(shipment)
                            .then(shipmentEventPublisher.publishShipmentDeliveredEvent(new ShipmentDeliveredEvent(shipment.getId(), LocalDateTime.now())));
                });
    }
}
