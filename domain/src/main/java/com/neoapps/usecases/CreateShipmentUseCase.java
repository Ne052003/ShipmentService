package com.neoapps.usecases;

import com.neoapps.exceptions.CustomerNotFoundException;
import com.neoapps.exceptions.DomainException;
import com.neoapps.exceptions.OrderNotFoundException;
import com.neoapps.gateways.CustomerApiGateway;
import com.neoapps.gateways.OrderApiGateway;
import com.neoapps.gateways.ShipmentRepositoryGateway;
import com.neoapps.model.Shipment;
import com.neoapps.model.ShipmentStatus;
import com.neoapps.usecases.dtos.CreateShipmentRequest;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;

public class CreateShipmentUseCase {
    private final ShipmentRepositoryGateway shipmentRepositoryGateway;
    private final OrderApiGateway orderApiGateway;
    private final CustomerApiGateway customerApiGateway;

    public CreateShipmentUseCase(ShipmentRepositoryGateway shipmentRepositoryGateway, OrderApiGateway orderApiGateway, CustomerApiGateway customerApiGateway) {
        this.shipmentRepositoryGateway = shipmentRepositoryGateway;
        this.orderApiGateway = orderApiGateway;
        this.customerApiGateway = customerApiGateway;
    }

    public Mono<Long> createShipment(CreateShipmentRequest request) {

        return validateOrderExists(request.getOrderId())
                .then(validateShipmentAlreadyExists(request.getOrderId()))
                .then(validateCustomerExists(request.getCustomerId()))
                .then(customerApiGateway.getCustomerAddressById(request.getAddressId()))
                .doOnNext(address -> {
                    if (!Objects.equals(address.getCustomerId(), request.getCustomerId())) {
                        throw new DomainException("The address provided is not from the customer with id: " + request.getCustomerId());
                    }
                })
                .flatMap(address -> {
                    Shipment shipment = new Shipment();
                    shipment.setOrderId(request.getOrderId());
                    shipment.setCustomerId(request.getCustomerId());
                    shipment.setAddressId(address.getId());
                    shipment.setAddressSnap(address.toString());
                    shipment.setStatus(ShipmentStatus.RECEIVED);
                    shipment.setCreatedAt(LocalDateTime.now());

                    return shipmentRepositoryGateway.save(shipment);
                });
    }

    private Mono<Void> validateShipmentAlreadyExists(Long orderId) {
        return shipmentRepositoryGateway.existsByOrderId(orderId)
                .flatMap(exists -> exists ?
                        Mono.error(new DomainException("The shipment for order with id: " + orderId + " was already received")) :
                        Mono.empty());
    }

    private Mono<Void> validateOrderExists(Long orderId) {
        return orderApiGateway.existsById(orderId)
                .flatMap(exists -> exists ?
                        Mono.empty() :
                        Mono.error(new OrderNotFoundException(orderId)));
    }

    public Mono<Void> validateCustomerExists(Long customerId) {
        return customerApiGateway.existsById(customerId)
                .flatMap(exists -> exists ?
                        Mono.empty() :
                        Mono.error(new CustomerNotFoundException(customerId)));
    }
}
