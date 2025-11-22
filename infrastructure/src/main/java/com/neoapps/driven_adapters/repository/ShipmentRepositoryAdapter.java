package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.entities.ShipmentEntity;
import com.neoapps.driven_adapters.mappers.ShipmentMapper;
import com.neoapps.gateways.ShipmentRepositoryGateway;
import com.neoapps.model.Shipment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ShipmentRepositoryAdapter implements ShipmentRepositoryGateway {

    private final ShipmentReactiveRepository shipmentReactiveRepository;
    private final ShipmentMapper shipmentMapper;

    @Override
    public Mono<Long> save(Shipment shipment) {
        return shipmentReactiveRepository.save(shipmentMapper.toShipmentEntity(shipment)).map(ShipmentEntity::getId);
    }

    @Override
    public Flux<Shipment> getByCustomerId(Long customerId) {
        return shipmentReactiveRepository.findByCustomerId(customerId).map(shipmentMapper::toShipment);
    }

    @Override
    public Mono<Shipment> getById(Long id) {
        return shipmentReactiveRepository.findById(id).map(shipmentMapper::toShipment);
    }

    @Override
    public Mono<Boolean> existsByOrderId(Long orderId) {
        return shipmentReactiveRepository.existsByOrderId(orderId);
    }
}
