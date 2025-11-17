package com.constructora.domain.usecases;

import com.constructora.domain.model.Material;
import com.constructora.domain.ports.MaterialRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ListarMaterialesUseCase {
    private final MaterialRepository materialRepository;

    public ListarMaterialesUseCase(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Flux<Material> execute() {
        return materialRepository.findAll();
    }
}