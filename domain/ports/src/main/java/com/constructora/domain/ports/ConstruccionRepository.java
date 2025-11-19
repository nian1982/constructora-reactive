package com.constructora.domain.ports;

import com.constructora.domain.model.Construccion;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConstruccionRepository {

    Mono<Construccion> save(Construccion construccion);

    Mono<Construccion> findById(Long id);

    Flux<Construccion> findAll();

    Mono<Void> deleteById(Long id);

    Mono<Construccion> findByNombre(String nombre);

}
