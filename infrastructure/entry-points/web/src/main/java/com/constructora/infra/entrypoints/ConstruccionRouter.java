package com.constructora.infra.entrypoints;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ConstruccionRouter {

    @Bean
    public RouterFunction<ServerResponse> rutasConstruccion(ConstruccionHandler handler) {
        return route()
                .path("/api/construcciones", builder -> builder
                        .GET("/{id}", handler::obtener)
                        .GET(handler::listar))
                .build();
    }
}
