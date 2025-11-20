package com.constructora.infra.adapters.db;

import org.springframework.stereotype.Component;

import com.constructora.domain.model.Construccion;
import com.constructora.domain.ports.ConstruccionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ConstruccionRepositoryAdapter implements ConstruccionRepository {

    private final R2dbConstruccionRepository r2dbConstruccionRepository;

    public ConstruccionRepositoryAdapter(R2dbConstruccionRepository r2dbConstruccionRepository) {
        this.r2dbConstruccionRepository = r2dbConstruccionRepository;
    }

    private ConstruccionEntity toEntity(Construccion construccion) {
        ConstruccionEntity entity = new ConstruccionEntity();
        entity.setId(construccion.getId());
        return entity;
    }

    private Construccion toDomain(ConstruccionEntity entity) {
        Construccion construccion = new Construccion(entity.getId(), entity.getNombre(), entity.getDias());
        return construccion;
    }

    @Override
    public Mono<Construccion> save(Construccion construccion) {
        ConstruccionEntity entity = toEntity(construccion);
        return r2dbConstruccionRepository.save(entity).map(this::toDomain);
    }

    @Override
    public Mono<Construccion> findById(Long id) {
        return r2dbConstruccionRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Flux<Construccion> findAll() {
        return r2dbConstruccionRepository.findAll().map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return r2dbConstruccionRepository.deleteById(id);
    }

    @Override
    public Mono<Construccion> findByNombre(String nombre) {
        return r2dbConstruccionRepository.findByNombre(nombre).map(this::toDomain);
    }

}
