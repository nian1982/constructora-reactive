package com.constructora.infra.entrypoints;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.constructora.domain.model.Construccion;
import com.constructora.domain.usecases.ListarConstruccionesUseCase;
import com.constructora.domain.usecases.ObtenerConstruccionUseCase;

import reactor.core.publisher.Mono;

@Component
public class ConstruccionHandler {

    private final ListarConstruccionesUseCase listarConstruccionesUseCase;
    private final ObtenerConstruccionUseCase obtenerConstruccionUseCase;

    public ConstruccionHandler(ListarConstruccionesUseCase listarConstruccionesUseCase,
            ObtenerConstruccionUseCase obtenerConstruccionUseCase) {
        this.listarConstruccionesUseCase = listarConstruccionesUseCase;
        this.obtenerConstruccionUseCase = obtenerConstruccionUseCase;
    }

    public Mono<ServerResponse> listar(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(listarConstruccionesUseCase.execute(), Construccion.class);
    }

    public Mono<ServerResponse> obtener(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));

        return obtenerConstruccionUseCase.execute(id)
                .flatMap(construccion -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(construccion))
                .onErrorResume(e -> ServerResponse.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(e.getMessage()));

    }

}
