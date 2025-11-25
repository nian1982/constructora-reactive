
package com.constructora.infra.entrypoints;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import org.springframework.http.MediaType;
import com.constructora.domain.model.ApiResponse;
import com.constructora.domain.model.TipoConstruccion;
import com.constructora.domain.usecases.ListarTipoConstruccionUseCase;

import java.util.List;

@Component
public class TipoConstruccionHandler {

    private final ListarTipoConstruccionUseCase listarTipoConstruccionUseCase;

    public TipoConstruccionHandler(ListarTipoConstruccionUseCase listarTipoConstruccionUseCase) {
        this.listarTipoConstruccionUseCase = listarTipoConstruccionUseCase;
    }

    public Mono<ServerResponse> listar(ServerRequest request) {
        String url = request.path();

        return listarTipoConstruccionUseCase.execute()
                .collectList()
                .flatMap(tipos -> {
                    ApiResponse<List<TipoConstruccion>> response = ApiResponse.success(200, url,
                            "Tipos de construcción obtenidos exitosamente", tipos);
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
