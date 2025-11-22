package com.neoapps.driven_adapters.external;

import com.neoapps.exceptions.ExternalServiceException;
import com.neoapps.gateways.OrderApiGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service
public class OrderApiService implements OrderApiGateway {

    private final WebClient webClient;
    private static final Logger logger = LoggerFactory.getLogger(OrderApiService.class);

    public OrderApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(System.getenv("ORDER_SERVICE_URL"))
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
                    return Mono.error(new ExternalServiceException("Error calling OrderApiService", ex));
                });
    }
}
