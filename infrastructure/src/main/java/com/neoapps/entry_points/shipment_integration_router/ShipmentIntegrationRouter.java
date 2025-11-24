package com.neoapps.entry_points.shipment_integration_router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class ShipmentIntegrationRouter {

    @Bean
    public RouterFunction<ServerResponse> shipmentRoutes(ShipmentIntegrationHandler handler) {
        return RouterFunctions
                .route(GET("/api/v1/shipment-ms/{id}"), handler::getShipmentById);
    }
}
