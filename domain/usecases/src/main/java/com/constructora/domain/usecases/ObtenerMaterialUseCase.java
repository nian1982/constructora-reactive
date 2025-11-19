package com.constructora.domain.usecases;

import org.springframework.stereotype.Service;

import com.constructora.domain.model.Material;
import com.constructora.domain.ports.MaterialRepository;

import reactor.core.publisher.Mono;

@Service
public class ObtenerMaterialUseCase {

    private final MaterialRepository materialRepository;

    public ObtenerMaterialUseCase(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Mono<Material> execute(Long id) {
        return materialRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException(
                        "No existe un material con id " + id)));
    }
}
