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

    // Método para mapear de Entity de Dominio a Entity de BD
    private MaterialEntity toEntity(Material material) {
        MaterialEntity entity = new MaterialEntity();
        entity.setId(material.getId());
        entity.setNombre(material.getNombre());
        entity.setSigla(material.getSigla());
        entity.setCantidad(material.getCantidad());
        return entity;
    }

    // Método para mapear de Entity de BD a Entity de Dominio
    private Material toDomain(MaterialEntity entity) {
        Material material = new Material();
        material.setId(entity.getId());
        material.setNombre(entity.getNombre());
        material.setSigla(entity.getSigla());
        material.setCantidad(entity.getCantidad());
        return material;
    }

    @Override
    public Mono<Material> save(Material material) {
        // 1. Convertir el Material de dominio a MaterialEntity
        MaterialEntity entityToSave = toEntity(material);
        // 2. Guardar la Entity usando el repositorio de Spring
        return r2dbcMaterialRepository.save(entityToSave)
                // 3. Convertir el resultado de vuelta a Material de dominio
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