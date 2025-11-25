package com.constructora.infra.entrypoints;

import com.constructora.domain.model.ApiResponse;
import com.constructora.domain.model.ConstruccionReporte;
import com.constructora.domain.model.Orden;
import com.constructora.domain.usecases.ConsultarFechaFinProyectoUseCase;
import com.constructora.domain.usecases.CrearOrdenUseCase;
import com.constructora.domain.usecases.GenerarReporteConstruccionesUseCase;
import com.constructora.domain.usecases.ListarOrdenesUseCase;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrdenHandler {

    private final CrearOrdenUseCase crearOrdenUseCase;
    private final ConsultarFechaFinProyectoUseCase consultarFechaFinProyectoUseCase;
    private final ListarOrdenesUseCase listarOrdenesUseCase;
    private final GenerarReporteConstruccionesUseCase generarReporteConstruccionesUseCase;

    public OrdenHandler(CrearOrdenUseCase crearOrdenUseCase,
            ConsultarFechaFinProyectoUseCase consultarFechaFinProyectoUseCase,
            ListarOrdenesUseCase listarOrdenesUseCase,
            GenerarReporteConstruccionesUseCase generarReporteConstruccionesUseCase) {
        this.crearOrdenUseCase = crearOrdenUseCase;
        this.consultarFechaFinProyectoUseCase = consultarFechaFinProyectoUseCase;
        this.listarOrdenesUseCase = listarOrdenesUseCase;
        this.generarReporteConstruccionesUseCase = generarReporteConstruccionesUseCase;
    }

    public Mono<ServerResponse> crear(ServerRequest request) {
        String rol = request.headers().firstHeader("rol");
        String url = request.path();

        if (rol == null) {
            ApiResponse<Void> response = ApiResponse.error(400, url, "El encabezado 'rol' es obligatorio.");
            return ServerResponse.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(response);
        }

        return request.bodyToMono(OrdenRequest.class)
                .flatMap(ordenRequest -> {
                    Orden orden = new Orden(null,
                            ordenRequest.getCoordenadaX(),
                            ordenRequest.getCoordenadaY(),
                            LocalDateTime.now(),
                            null,
                            null,
                            null,
                            ordenRequest.getConstruccionId());
                    return crearOrdenUseCase.execute(orden, rol);
                })
                .flatMap(ordenCreada -> {
                    ApiResponse<Orden> response = ApiResponse.success(201, url, "Orden creada exitosamente",
                            ordenCreada);
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(response);
                })
                .onErrorResume(e -> {
                    ApiResponse<Void> response = ApiResponse.error(400, url, e.getMessage());
                    return ServerResponse.badRequest()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(response);
                });
    }

    public Mono<ServerResponse> listar(ServerRequest request) {
        String url = request.path();

        return listarOrdenesUseCase.execute()
                .collectList()
                .flatMap(ordenes -> {
                    ApiResponse<List<Orden>> response = ApiResponse.success(200, url,
                            "Órdenes obtenidas exitosamente", ordenes);
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(response);
                })
                .onErrorResume(e -> {
                    ApiResponse<Void> response = ApiResponse.error(500, url, e.getMessage());
                    return ServerResponse.status(500)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(response);
                });
    }

    public Mono<ServerResponse> consultarFechaFin(ServerRequest request) {
        String url = request.path();

        return consultarFechaFinProyectoUseCase.execute()
                .flatMap(fecha -> {
                    ApiResponse<LocalDateTime> response = ApiResponse.success(200, url,
                            "Fecha de finalización obtenida exitosamente", fecha);
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(response);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    ApiResponse<Void> response = ApiResponse.success(200, url, "No hay proyecto en curso", null);
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(response);
                }))
                .onErrorResume(e -> {
                    ApiResponse<Void> response = ApiResponse.error(500, url, e.getMessage());
                    return ServerResponse.status(500)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(response);
                });
    }

    public Mono<ServerResponse> generarReporte(ServerRequest request) {
        String url = request.path();

        return generarReporteConstruccionesUseCase.execute()
                .flatMap(reporte -> {
                    ApiResponse<ConstruccionReporte> response = ApiResponse.success(200, url,
                            "Reporte generado exitosamente", reporte);
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(response);
                })
                .onErrorResume(e -> {
                    ApiResponse<Void> response = ApiResponse.error(500, url, e.getMessage());
                    return ServerResponse.status(500)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(response);
                });
    }
}
