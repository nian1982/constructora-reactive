package com.constructora.infra.adapters.db;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface R2dbcOrdenRepository extends R2dbcRepository<OrdenEntity, Long> {
    Flux<OrdenEntity> findByCoordenadaXAndCoordenadaY(Double coordenadaX, Double coordenadaY);

    @Query("SELECT * FROM solicitudes ORDER BY fecha_finalizacion DESC LIMIT 1")
    Mono<OrdenEntity> findLastEndingOrder();
}
