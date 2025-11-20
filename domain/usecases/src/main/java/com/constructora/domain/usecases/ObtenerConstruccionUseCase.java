package com.constructora.domain.usecases;

import org.springframework.stereotype.Service;

import com.constructora.domain.model.Construccion;
import com.constructora.domain.ports.ConstruccionRepository;

import reactor.core.publisher.Mono;

@Service
public class ObtenerConstruccionUseCase {

    private final ConstruccionRepository construccionRepository;

    public ObtenerConstruccionUseCase(ConstruccionRepository construccionRepository) {
        this.construccionRepository = construccionRepository;
    }

    public Mono<Construccion> execute(Long id) {
        return construccionRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("No existe una construccion con el id " + id)));
    }

}
