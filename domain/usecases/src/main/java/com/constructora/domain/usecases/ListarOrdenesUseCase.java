package com.constructora.domain.usecases;

import com.constructora.domain.model.Orden;
import com.constructora.domain.ports.OrdenRepository;
import reactor.core.publisher.Flux;

public class ListarOrdenesUseCase {

    private final OrdenRepository ordenRepository;

    public ListarOrdenesUseCase(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    public Flux<Orden> execute() {
        return ordenRepository.findAll();
    }
}
