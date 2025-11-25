package com.constructora.infra.entrypoints;

import com.constructora.domain.model.Orden;
import com.constructora.domain.usecases.ConsultarFechaFinProyectoUseCase;
import com.constructora.domain.usecases.CrearOrdenUseCase;
import com.constructora.domain.usecases.ListarOrdenesUseCase;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class OrdenHandler {

    private final CrearOrdenUseCase crearOrdenUseCase;
    private final ConsultarFechaFinProyectoUseCase consultarFechaFinProyectoUseCase;
    private final ListarOrdenesUseCase listarOrdenesUseCase;

    public OrdenHandler(CrearOrdenUseCase crearOrdenUseCase,
            ConsultarFechaFinProyectoUseCase consultarFechaFinProyectoUseCase,
            ListarOrdenesUseCase listarOrdenesUseCase) {
        this.crearOrdenUseCase = crearOrdenUseCase;
        this.consultarFechaFinProyectoUseCase = consultarFechaFinProyectoUseCase;
        this.listarOrdenesUseCase = listarOrdenesUseCase;
    }

    public Mono<ServerResponse> crear(ServerRequest request) {
        String rol = request.headers().firstHeader("rol");
        if (rol == null) {
            return ServerResponse.badRequest().bodyValue("El encabezado 'rol' es obligatorio.");
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
                .flatMap(ordenCreada -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(ordenCreada))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }

    public Mono<ServerResponse> listar(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(listarOrdenesUseCase.execute(), Orden.class);
    }

    public Mono<ServerResponse> consultarFechaFin(ServerRequest request) {
        return consultarFechaFinProyectoUseCase.execute()
                .flatMap(fecha -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(fecha))
                .switchIfEmpty(ServerResponse.ok().bodyValue("No hay proyecto en curso."));
    }
}
