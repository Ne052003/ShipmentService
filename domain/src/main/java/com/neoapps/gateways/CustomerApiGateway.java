package com.neoapps.gateways;

import com.neoapps.model.Address;
import reactor.core.publisher.Mono;

public interface CustomerApiGateway {
    Mono<Boolean> existsById(Long id);
    Mono<Address> getCustomerAddressById(Long id);
}
