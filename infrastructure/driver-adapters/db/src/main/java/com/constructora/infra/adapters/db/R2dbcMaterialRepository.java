package com.constructora.infra.adapters.db;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

// Ahora extiende R2dbcRepository para la Entity de infraestructura
@Repository
public interface R2dbcMaterialRepository extends R2dbcRepository<MaterialEntity, Long> {
    // Spring Data implementa este método basado en el nombre
    Mono<MaterialEntity> findByNombre(String nombre);
}