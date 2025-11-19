package com.constructora.infra.entrypoints;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.constructora.domain.model.Construccion;
import com.constructora.domain.usecases.ListarConstruccionesUseCase;

import reactor.core.publisher.Mono;

@Component
public class ConstruccionHandler {

    private final ListarConstruccionesUseCase listarConstruccionesUseCase;

    public ConstruccionHandler(ListarConstruccionesUseCase listarConstruccionesUseCase) {
        this.listarConstruccionesUseCase = listarConstruccionesUseCase;
    }

    public Mono<ServerResponse> listar(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(listarConstruccionesUseCase.execute(), Construccion.class);
    }

}
