package com.constructora.domain.usecases;

import com.constructora.domain.model.Material;
import com.constructora.domain.ports.MaterialRepository;

import reactor.core.publisher.Flux;

public class ListarMaterialesUseCase {
    private final MaterialRepository materialRepository;

    public ListarMaterialesUseCase(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Flux<Material> execute() {
        return materialRepository.findAll();
    }
}