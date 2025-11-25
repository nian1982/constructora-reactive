package com.constructora.infra.adapters.db;

import com.constructora.domain.model.TipoConstruccion;
import com.constructora.domain.ports.TipoConstruccionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.constructora.domain.model.Construccion;
import com.constructora.domain.model.Material;

@Component
public class TipoConstruccionAdapter implements TipoConstruccionRepository {

    private final R2dbTipoConstruccionRepository r2dbTipoConstruccionRepository;

    public TipoConstruccionAdapter(R2dbTipoConstruccionRepository r2dbTipoConstruccionRepository) {
        this.r2dbTipoConstruccionRepository = r2dbTipoConstruccionRepository;
    }

    private TipoConstruccionEntity toEntity(TipoConstruccion tipoConstruccion) {
        TipoConstruccionEntity entity = new TipoConstruccionEntity();
        entity.setId(tipoConstruccion.getId());
        entity.setCantidad(tipoConstruccion.getCantidad());
        entity.setConstruccionId(tipoConstruccion.getConstruccion().getId());
        entity.setMaterialId(tipoConstruccion.getMaterial().getId());
        return entity;
    }

    private TipoConstruccion toDomain(TipoConstruccionEntity entity) {
        return new TipoConstruccion(
                entity.getId(),
                entity.getCantidad(),
                new Construccion(entity.getConstruccionId(), null, 0),
                new Material(entity.getMaterialId(), null, null, null));
    }

    @Override
    public Mono<TipoConstruccion> save(TipoConstruccion tipoConstruccion) {
        TipoConstruccionEntity entityToSave = toEntity(tipoConstruccion);
        return r2dbTipoConstruccionRepository.save(entityToSave)
                .map(this::toDomain);
    }

    @Override
    public Mono<TipoConstruccion> findById(Long id) {
        return r2dbTipoConstruccionRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<TipoConstruccion> findAll() {
        return r2dbTipoConstruccionRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return r2dbTipoConstruccionRepository.deleteById(id);
    }

    @Override
    public Flux<TipoConstruccion> findByConstruccionId(Long construccionId) {
        return r2dbTipoConstruccionRepository.findByConstruccionId(construccionId)
                .map(this::toDomain);
    }

}
