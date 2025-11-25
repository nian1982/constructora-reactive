package com.constructora.infra.adapters.db;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface R2dbcOrdenRepository extends R2dbcRepository<OrdenEntity, Long> {
    Flux<OrdenEntity> findByCoordenadaXAndCoordenadaY(Double coordenadaX, Double coordenadaY);

    @Query("SELECT * FROM solicitudes WHERE estado = 'FINALIZADA' ORDER BY fecha_finalizacion DESC LIMIT 1")
    Mono<OrdenEntity> findLastEndingOrder();

    Mono<Long> countByEstado(String estado);

    @Query("SELECT s.*, c.nombre as construccion_nombre FROM solicitudes s " +
            "INNER JOIN construcciones c ON s.construccion_id = c.id " +
            "WHERE s.estado = 'FINALIZADA'")
    Flux<OrdenEntity> findCompletedOrdersWithConstruccion();

}
