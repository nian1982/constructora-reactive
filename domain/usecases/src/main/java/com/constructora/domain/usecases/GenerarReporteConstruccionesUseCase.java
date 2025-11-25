package com.constructora.domain.usecases;

import com.constructora.domain.model.ConstruccionReporte;
import com.constructora.domain.ports.ConstruccionRepository;
import com.constructora.domain.ports.OrdenRepository;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class GenerarReporteConstruccionesUseCase {

    private final OrdenRepository ordenRepository;
    private final ConstruccionRepository construccionRepository;

    public GenerarReporteConstruccionesUseCase(OrdenRepository ordenRepository,
            ConstruccionRepository construccionRepository) {
        this.ordenRepository = ordenRepository;
        this.construccionRepository = construccionRepository;
    }

    public Mono<ConstruccionReporte> execute() {
        Mono<Long> pendientes = ordenRepository.countByEstado("PROGRAMADA");
        Mono<Long> enProgreso = ordenRepository.countByEstado("EN_PROGRESO");

        Mono<Map<String, Long>> terminadasPorTipo = ordenRepository.findCompletedOrdersWithConstruccion()
                .flatMap(orden -> construccionRepository.findById(orden.getConstruccionId())
                        .map(construccion -> construccion.getNombre()))
                .collectMultimap(nombre -> nombre, nombre -> 1L)
                .map(multimap -> {
                    Map<String, Long> result = new HashMap<>();
                    multimap.forEach((nombre, counts) -> result.put(nombre, (long) counts.size()));
                    return result;
                });

        return Mono.zip(pendientes, enProgreso, terminadasPorTipo)
                .map(tuple -> new ConstruccionReporte(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }
}
