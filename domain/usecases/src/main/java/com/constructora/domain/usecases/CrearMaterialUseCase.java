package com.constructora.domain.usecases;

import com.constructora.domain.model.Material;
import com.constructora.domain.ports.MaterialRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CrearMaterialUseCase {
    private final MaterialRepository materialRepository;

    public CrearMaterialUseCase(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Mono<Material> execute(Material material) {
        // Validar que la sigla tenga exactamente 2 caracteres
        if (material.getSigla() == null || material.getSigla().length() != 2) {
            return Mono.error(new IllegalArgumentException("La sigla debe tener exactamente 2 caracteres."));
        }
        // Verificar si el nombre ya existe antes de guardar
        return materialRepository.findByNombre(material.getNombre())
                .flatMap(existingMaterial -> Mono.<Material>error(new IllegalArgumentException(
                        "El material con nombre '" + material.getNombre() + "' ya existe.")))
                .switchIfEmpty(materialRepository.save(material));
    }
}
