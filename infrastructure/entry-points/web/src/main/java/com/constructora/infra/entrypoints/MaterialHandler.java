package com.constructora.infra.entrypoints;

import com.constructora.domain.model.Material;
import com.constructora.domain.usecases.CrearMaterialUseCase;
import com.constructora.domain.usecases.ListarMaterialesUseCase;
import com.constructora.domain.usecases.ObtenerMaterialUseCase;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MaterialHandler {

    private final CrearMaterialUseCase crearMaterialUseCase;
    private final ListarMaterialesUseCase listarMaterialesUseCase;
    private final ObtenerMaterialUseCase obtenerMaterialUseCase;

    public MaterialHandler(CrearMaterialUseCase crearMaterialUseCase,
            ListarMaterialesUseCase listarMaterialesUseCase,
            ObtenerMaterialUseCase obtenerMaterialUseCase) {
        this.crearMaterialUseCase = crearMaterialUseCase;
        this.listarMaterialesUseCase = listarMaterialesUseCase;
        this.obtenerMaterialUseCase = obtenerMaterialUseCase;
    }

    public Mono<ServerResponse> crear(ServerRequest request) {
        Mono<Material> materialMono = request.bodyToMono(Material.class);
        return materialMono.flatMap(crearMaterialUseCase::execute)
                .flatMap(material -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(material))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }

    public Mono<ServerResponse> listar(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(listarMaterialesUseCase.execute(), Material.class);
    }

    public Mono<ServerResponse> obtener(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));

        return obtenerMaterialUseCase.execute(id)
                .flatMap(material -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(material))
                .onErrorResume(e -> ServerResponse.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(e.getMessage()));
    }

}
