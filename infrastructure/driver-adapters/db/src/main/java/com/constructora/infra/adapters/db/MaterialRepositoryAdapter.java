package com.constructora.infra.adapters.db;

import com.constructora.domain.model.Material;
import com.constructora.domain.ports.MaterialRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MaterialRepositoryAdapter implements MaterialRepository {

    private final R2dbcMaterialRepository r2dbcMaterialRepository;

    public MaterialRepositoryAdapter(R2dbcMaterialRepository r2dbcMaterialRepository) {
        this.r2dbcMaterialRepository = r2dbcMaterialRepository;
    }

    private MaterialEntity toEntity(Material material) {
        MaterialEntity entity = new MaterialEntity();
        entity.setId(material.getId());
        entity.setNombre(material.getNombre());
        entity.setSigla(material.getSigla());
        entity.setCantidad(material.getCantidad());
        return entity;
    }

    private Material toDomain(MaterialEntity entity) {
        Material material = new Material(
                entity.getId(),
                entity.getNombre(),
                entity.getSigla(),
                entity.getCantidad());

        return material;
    }

    @Override
    public Mono<Material> save(Material material) {        
        MaterialEntity entityToSave = toEntity(material);
        return r2dbcMaterialRepository.save(entityToSave)
                .map(this::toDomain);
    }

    @Override
    public Mono<Material> findById(Long id) {
        return r2dbcMaterialRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Material> findAll() {
        return r2dbcMaterialRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return r2dbcMaterialRepository.deleteById(id);
    }

    @Override
    public Mono<Material> findByNombre(String nombre) {
        return r2dbcMaterialRepository.findByNombre(nombre)
                .map(this::toDomain);
    }
}