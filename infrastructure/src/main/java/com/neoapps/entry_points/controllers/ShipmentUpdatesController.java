package com.neoapps.entry_points.controllers;

import com.neoapps.usecases.UpdateShipmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/shipment-updates")
@RequiredArgsConstructor
public class ShipmentUpdatesController {
    private final UpdateShipmentUseCase updateShipmentUseCase;

    @PutMapping("/toPendingShipping/{id}")
    public Mono<ResponseEntity<Void>> updateShipmentStatusToPendingShipping(@PathVariable("id") Long id) {
        return updateShipmentUseCase.setShipmentStatusToPendingShipping(id)
                .thenReturn(ResponseEntity.ok().build());
    }

    @PutMapping("/toOnTheWay/{id}")
    public Mono<ResponseEntity<Void>> updateShipmentStatusToOnTheWay(@PathVariable("id") Long id) {
        return updateShipmentUseCase.setShipmentStatusToOnTheWay(id)
                .thenReturn(ResponseEntity.ok().build());
    }

    @PutMapping("/toDelivered/{id}")
    public Mono<ResponseEntity<Void>> updateShipmentStatusToDelivered(@PathVariable("id") Long id) {
        return updateShipmentUseCase.setShipmentStatusToDelivered(id)
                .thenReturn(ResponseEntity.ok().build());
    }
}
