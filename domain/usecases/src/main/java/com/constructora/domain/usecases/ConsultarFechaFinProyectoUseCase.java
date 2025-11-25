package com.constructora.domain.usecases;

import com.constructora.domain.ports.OrdenRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class ConsultarFechaFinProyectoUseCase {

    private final OrdenRepository ordenRepository;

    public ConsultarFechaFinProyectoUseCase(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    public Mono<LocalDateTime> execute() {
        return ordenRepository.findLastEndingOrder()
                .map(orden -> orden.getFechaFinalizacion())
                .switchIfEmpty(Mono.empty());
    }
}
