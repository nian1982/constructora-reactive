package com.constructora.domain.usecases;

import com.constructora.domain.model.TipoConstruccion;
import com.constructora.domain.ports.TipoConstruccionRepository;

import reactor.core.publisher.Flux;

public class ListarTipoConstruccionUseCase {

    private final TipoConstruccionRepository tipoConstruccionRepository;

    public ListarTipoConstruccionUseCase(TipoConstruccionRepository tipoConstruccionRepository) {
        this.tipoConstruccionRepository = tipoConstruccionRepository;
    }

    public Flux<TipoConstruccion> execute() {
        return tipoConstruccionRepository.findAll();
    }

}
