package com.neoapps.gateways;

import reactor.core.publisher.Mono;

public interface OrderApiGateway {
    Mono<Boolean> existsById(Long id);
}
