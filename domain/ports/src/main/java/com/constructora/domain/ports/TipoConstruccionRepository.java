package com.constructora.domain.ports;

import com.constructora.domain.model.TipoConstruccion;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TipoConstruccionRepository {

    Mono<TipoConstruccion> save(TipoConstruccion tipoConstruccion);

    Mono<TipoConstruccion> findById(Long id);

    Flux<TipoConstruccion> findAll();

    Mono<Void> deleteById(Long id);

}
