package com.constructora.domain.usecases;

import com.constructora.domain.model.Orden;
import com.constructora.domain.model.TipoConstruccion;
import com.constructora.domain.ports.ConstruccionRepository;
import com.constructora.domain.ports.MaterialRepository;
import com.constructora.domain.ports.OrdenRepository;
import com.constructora.domain.ports.TipoConstruccionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CrearOrdenUseCase {

        private final OrdenRepository ordenRepository;
        private final TipoConstruccionRepository tipoConstruccionRepository;
        private final MaterialRepository materialRepository;
        private final ConstruccionRepository construccionRepository;

        private static final String ROL_ARQUITECTO = "Arquitecto";

        public CrearOrdenUseCase(OrdenRepository ordenRepository,
                        TipoConstruccionRepository tipoConstruccionRepository,
                        MaterialRepository materialRepository,
                        ConstruccionRepository construccionRepository) {
                this.ordenRepository = ordenRepository;
                this.tipoConstruccionRepository = tipoConstruccionRepository;
                this.materialRepository = materialRepository;
                this.construccionRepository = construccionRepository;
        }

        public Mono<Orden> execute(Orden orden, String rol) {
                if (!ROL_ARQUITECTO.equalsIgnoreCase(rol)) {
                        return Mono.error(new IllegalArgumentException(
                                        "Solo el " + ROL_ARQUITECTO + " puede crear ordenes de construccion."));
                }

                return ordenRepository.findByCoordinates(orden.getCoordenadaX(), orden.getCoordenadaY())
                                .hasElements()
                                .flatMap(exists -> {
                                        if (exists) {
                                                return Mono
                                                                .error(new IllegalArgumentException(
                                                                                "Ya existe una orden en las coordenadas dadas."));
                                        }
                                        return processOrder(orden);
                                });
        }

        private Mono<Orden> processOrder(Orden orden) {
                return tipoConstruccionRepository.findByConstruccionId(orden.getConstruccionId())
                                .collectList()
                                .flatMap(tipos -> validateAndDeductMaterials(tipos)
                                                .then(calculateDates(orden))
                                                .flatMap(ordenRepository::save));
        }

        private Mono<Void> validateAndDeductMaterials(List<TipoConstruccion> tipos) {
                Map<Long, Integer> requiredMaterials = tipos.stream()
                                .collect(Collectors.toMap(tc -> tc.getMaterial().getId(),
                                                TipoConstruccion::getCantidad));

                return Flux.fromIterable(requiredMaterials.entrySet())
                                .flatMap(entry -> materialRepository.findById(entry.getKey())
                                                .flatMap(material -> {
                                                        if (material.getCantidad() < entry.getValue()) {
                                                                return Mono.error(new IllegalArgumentException(
                                                                                "No hay suficiente material: "
                                                                                                + material.getNombre()));
                                                        }
                                                        material.descontar(entry.getValue());
                                                        return materialRepository.save(material);
                                                }))
                                .then();
        }

        private Mono<Orden> calculateDates(Orden orden) {
                return construccionRepository.findById(orden.getConstruccionId())
                                .flatMap(construccion -> ordenRepository.findLastEndingOrder()
                                                .map(Orden::getFechaFinalizacion)
                                                .defaultIfEmpty(orden.getFechaSolicitud())
                                                .map(lastEndDate -> {
                                                        LocalDateTime startDate = lastEndDate
                                                                        .isAfter(orden.getFechaSolicitud())
                                                                                        ? lastEndDate.plusDays(1)
                                                                                        : orden.getFechaSolicitud()
                                                                                                        .plusDays(1);

                                                        LocalDateTime endDate = startDate
                                                                        .plusDays(construccion.getDias());

                                                        return orden.withFechas(startDate, endDate)
                                                                        .withEstado("PENDIENTE");
                                                }));
        }
}
