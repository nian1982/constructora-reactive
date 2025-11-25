package com.constructora.infra.adapters.db;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface R2dbcMaterialRepository extends R2dbcRepository<MaterialEntity, Long> {
    Mono<MaterialEntity> findByNombre(String nombre);
}