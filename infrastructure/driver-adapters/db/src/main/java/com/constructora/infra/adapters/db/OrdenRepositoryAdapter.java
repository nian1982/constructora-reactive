package com.constructora.infra.adapters.db;

import com.constructora.domain.model.Orden;
import com.constructora.domain.ports.OrdenRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class OrdenRepositoryAdapter implements OrdenRepository {

    private final R2dbcOrdenRepository r2dbcOrdenRepository;

    public OrdenRepositoryAdapter(R2dbcOrdenRepository r2dbcOrdenRepository) {
        this.r2dbcOrdenRepository = r2dbcOrdenRepository;
    }

    @Override
    public Mono<Orden> save(Orden orden) {
        return r2dbcOrdenRepository.save(toEntity(orden))
                .map(this::toDomain);
    }

    @Override
    public Flux<Orden> findByCoordinates(Double x, Double y) {
        return r2dbcOrdenRepository.findByCoordenadaXAndCoordenadaY(x, y)
                .map(this::toDomain);
    }

    @Override
    public Mono<Orden> findLastEndingOrder() {
        return r2dbcOrdenRepository.findLastEndingOrder()
                .map(this::toDomain);
    }

    @Override
    public Flux<Orden> findAll() {
        return r2dbcOrdenRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Long> countByEstado(String estado) {
        return r2dbcOrdenRepository.countByEstado(estado);
    }

    @Override
    public Flux<Orden> findCompletedOrdersWithConstruccion() {
        return r2dbcOrdenRepository.findCompletedOrdersWithConstruccion()
                .map(this::toDomain);
    }

    private OrdenEntity toEntity(Orden orden) {
        return new OrdenEntity(
                orden.getId(),
                orden.getCoordenadaX(),
                orden.getCoordenadaY(),
                orden.getFechaSolicitud(),
                orden.getFechaInicio(),
                orden.getFechaFinalizacion(),
                orden.getEstado(),
                orden.getConstruccionId());
    }

    private Orden toDomain(OrdenEntity entity) {
        return new Orden(
                entity.getId(),
                entity.getCoordenadaX(),
                entity.getCoordenadaY(),
                entity.getFechaSolicitud(),
                entity.getFechaInicio(),
                entity.getFechaFinalizacion(),
                entity.getEstado(),
                entity.getConstruccionId());
    }
}
