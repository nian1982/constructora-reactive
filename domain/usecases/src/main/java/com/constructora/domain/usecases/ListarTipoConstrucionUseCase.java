package com.constructora.domain.usecases;

import com.constructora.domain.model.TipoConstruccion;
import com.constructora.domain.ports.TipoConstruccionRepository;

import reactor.core.publisher.Flux;

public class ListarTipoConstrucionUseCase {

    private final TipoConstruccionRepository tipoConstruccionRepository;

    public ListarTipoConstrucionUseCase(TipoConstruccionRepository tipoConstruccionRepository) {
        this.tipoConstruccionRepository = tipoConstruccionRepository;
    }

    public Flux<TipoConstruccion> execute() {
        return tipoConstruccionRepository.findAll();
    }

}
