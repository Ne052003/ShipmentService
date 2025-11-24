package com.neoapps.usecases;

import com.neoapps.exceptions.InvalidInputException;
import com.neoapps.gateways.ShipmentRepositoryGateway;
import com.neoapps.model.Shipment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GetShipmentUseCase {

    private final ShipmentRepositoryGateway shipmentRepositoryGateway;

    public GetShipmentUseCase(ShipmentRepositoryGateway shipmentRepositoryGateway) {
        this.shipmentRepositoryGateway = shipmentRepositoryGateway;
    }

    public Mono<Shipment> getShipmentById(Long id) {

        if (id == null) {
            return Mono.error(new InvalidInputException("The id of the shipment can't be null"));
        }

        return shipmentRepositoryGateway.getById(id);
    }

    public Flux<Shipment> getShipmentByCustomerId(Long id) {

        if (id == null) {
            return Flux.error(new InvalidInputException("The id of the shipment can't be null"));
        }

        return shipmentRepositoryGateway.getByCustomerId(id);
    }
}
