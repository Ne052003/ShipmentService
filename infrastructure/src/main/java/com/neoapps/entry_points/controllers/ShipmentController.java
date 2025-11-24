package com.neoapps.entry_points.controllers;

import com.neoapps.model.Shipment;
import com.neoapps.usecases.CreateShipmentUseCase;
import com.neoapps.usecases.GetShipmentUseCase;
import com.neoapps.usecases.dtos.CreateShipmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/shipments")
@RequiredArgsConstructor
public class ShipmentController {
    private final CreateShipmentUseCase createShipmentUseCase;
    private final GetShipmentUseCase getShipmentUseCase;

    @PostMapping
    public Mono<ResponseEntity<String>> createShipment(@RequestBody CreateShipmentRequest createShipmentRequest) {
        return createShipmentUseCase.createShipment(createShipmentRequest).map(shipmentId -> ResponseEntity.ok("Shipment saved with id: " + shipmentId));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Shipment>> getShipmentById(@PathVariable("id") Long id) {
        return getShipmentUseCase.getShipmentById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public Mono<ResponseEntity<Flux<Shipment>>> getShipmentsByCustomerId(@PathVariable("customerId") Long customerId) {
        Flux<Shipment> shipmentFlux = getShipmentUseCase.getShipmentByCustomerId(customerId);

        return shipmentFlux.hasElements()
                .map(hasElements -> hasElements ? ResponseEntity.ok(shipmentFlux) : ResponseEntity.notFound().build());
    }
}
