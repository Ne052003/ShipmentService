package com.neoapps.gateways;

import com.neoapps.model.Shipment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShipmentRepositoryGateway {

    Mono<Long> save(Shipment shipment);

    Flux<Shipment> getByCustomerId(Long customerId);

    Mono<Shipment> getById(Long id);

    Mono<Boolean> existsByOrderId(Long orderId);
}
