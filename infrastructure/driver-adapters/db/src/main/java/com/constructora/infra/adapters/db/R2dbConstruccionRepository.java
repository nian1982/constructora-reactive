package com.constructora.infra.adapters.db;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface R2dbConstruccionRepository extends R2dbcRepository<ConstruccionEntity, Long> {

    Mono<ConstruccionEntity> findByNombre(String nombre);

}
