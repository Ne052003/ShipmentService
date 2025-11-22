package com.neoapps.driven_adapters.external;

import com.neoapps.exceptions.ExternalServiceException;
import com.neoapps.gateways.CustomerApiGateway;
import com.neoapps.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service
public class CustomerApiService implements CustomerApiGateway {

    private static final Logger logger = LoggerFactory.getLogger(CustomerApiService.class);
    private final WebClient webClient;

    public CustomerApiService(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder
                .baseUrl(System.getenv("CUSTOMER_SERVICE_URL"))
                .build();
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return webClient
                .get()
                .uri("/exists/{id}", id)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(WebClientException.class, ex -> {
                    logger.error("WebClientException error: {}", ex.getCause() != null ? ex.getCause().getMessage() : "");
                    return Mono.error(new ExternalServiceException("Error calling CustomerApiService", ex));
                });
    }

    @Override
    public Mono<Address> getCustomerAddressById(Long id) {
        return webClient
                .get()
                .uri("/addresses/{id}", id)
                .retrieve()
                .bodyToMono(Address.class);
    }
}
