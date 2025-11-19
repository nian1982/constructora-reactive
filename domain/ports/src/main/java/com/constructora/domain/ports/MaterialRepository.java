package com.constructora.domain.ports;

import com.constructora.domain.model.Material;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MaterialRepository {
    Mono<Material> save(Material material);

    Mono<Material> findById(Long id);

    Flux<Material> findAll();

    Mono<Void> deleteById(Long id);

    Mono<Material> findByNombre(String nombre);
}
