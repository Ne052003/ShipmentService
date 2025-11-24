package com.neoapps.entry_points.shipment_integration_router;

import com.neoapps.usecases.GetShipmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ShipmentIntegrationHandler {

    private final GetShipmentUseCase getShipmentUseCase;

    public Mono<ServerResponse> getShipmentById(ServerRequest request) {
        Long shipmentId = Long.valueOf(request.pathVariable("id"));

        return getShipmentUseCase.getShipmentById(shipmentId)
                .flatMap(shipment -> ServerResponse.ok().bodyValue(shipment));
    }
}
