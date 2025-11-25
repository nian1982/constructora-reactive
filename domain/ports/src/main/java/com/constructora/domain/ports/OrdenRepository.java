package com.constructora.domain.ports;

import com.constructora.domain.model.Orden;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrdenRepository {
    Mono<Orden> save(Orden orden);

    Flux<Orden> findByCoordinates(Double x, Double y);

    Mono<Orden> findLastEndingOrder();

    Flux<Orden> findAll();
}
