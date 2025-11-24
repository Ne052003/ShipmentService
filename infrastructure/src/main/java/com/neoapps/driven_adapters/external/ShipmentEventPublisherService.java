package com.neoapps.driven_adapters.external;

import com.neoapps.events.ShipmentDeliveredEvent;
import com.neoapps.events.ShipmentOnTheWayEvent;
import com.neoapps.events.ShipmentPendingEvent;
import com.neoapps.gateways.ShipmentEventPublisherGateway;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ShipmentEventPublisherService implements ShipmentEventPublisherGateway {

    private final SnsTemplate snsTemplate;

    @Override
    public Mono<Void> publishShipmentPendingEvent(ShipmentPendingEvent event) {
        return Mono.fromRunnable(() -> snsTemplate.sendNotification("shipmentPendingEvent", event, "The shipment is now pending"))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }

    @Override
    public Mono<Void> publishShipmentOnTheWayEvent(ShipmentOnTheWayEvent event) {
        return Mono.fromRunnable(() -> snsTemplate.sendNotification("shipmentOnTheWayEvent", event, "The shipment is now on the way"))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }

    @Override
    public Mono<Void> publishShipmentDeliveredEvent(ShipmentDeliveredEvent event) {
        return Mono.fromRunnable(() -> snsTemplate.sendNotification("shipmentDeliveredEvent", event, "The shipment was delivered"))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }
}
