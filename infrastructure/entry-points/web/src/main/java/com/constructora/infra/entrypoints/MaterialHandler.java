package com.constructora.infra.entrypoints;

import com.constructora.domain.model.ApiResponse;
import com.constructora.domain.model.Material;
import com.constructora.domain.usecases.CrearMaterialUseCase;
import com.constructora.domain.usecases.ListarMaterialesUseCase;
import com.constructora.domain.usecases.ObtenerMaterialUseCase;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

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
                String url = request.path();

                Mono<Material> materialMono = request.bodyToMono(Material.class);
                return materialMono.flatMap(crearMaterialUseCase::execute)
                                .flatMap(material -> {
                                        ApiResponse<Material> response = ApiResponse.success(201, url,
                                                        "Material creado exitosamente",
                                                        material);
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

                return listarMaterialesUseCase.execute()
                                .collectList()
                                .flatMap(materiales -> {
                                        ApiResponse<List<Material>> response = ApiResponse.success(200, url,
                                                        "Materiales obtenidos exitosamente", materiales);
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

                return obtenerMaterialUseCase.execute(id)
                                .flatMap(material -> {
                                        ApiResponse<Material> response = ApiResponse.success(200, url,
                                                        "Material obtenido exitosamente",
                                                        material);
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
