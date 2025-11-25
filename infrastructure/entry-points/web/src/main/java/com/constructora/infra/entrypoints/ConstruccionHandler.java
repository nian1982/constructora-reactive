package com.constructora.infra.entrypoints;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.constructora.domain.model.ApiResponse;
import com.constructora.domain.model.Construccion;
import com.constructora.domain.usecases.ListarConstruccionesUseCase;
import com.constructora.domain.usecases.ObtenerConstruccionUseCase;

import reactor.core.publisher.Mono;

import java.util.List;

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
        String url = request.path();

        return listarConstruccionesUseCase.execute()
                .collectList()
                .flatMap(construcciones -> {
                    ApiResponse<List<Construccion>> response = ApiResponse.success(200, url,
                            "Construcciones obtenidas exitosamente", construcciones);
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

    public Mono<ServerResponse> obtener(ServerRequest request) {
        String url = request.path();
        Long id = Long.valueOf(request.pathVariable("id"));

        return obtenerConstruccionUseCase.execute(id)
                .flatMap(construccion -> {
                    ApiResponse<Construccion> response = ApiResponse.success(200, url,
                            "Construcción obtenida exitosamente", construccion);
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(response);
                })
                .onErrorResume(e -> {
                    ApiResponse<Void> response = ApiResponse.error(404, url, e.getMessage());
                    return ServerResponse.status(404)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(response);
                });

    }

}
