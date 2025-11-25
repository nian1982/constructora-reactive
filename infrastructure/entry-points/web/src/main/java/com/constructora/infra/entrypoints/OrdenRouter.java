package com.constructora.infra.entrypoints;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class OrdenRouter {

    @Bean
    public RouterFunction<ServerResponse> rutasOrden(OrdenHandler handler) {
        return route()
                .path("/api/ordenes", builder -> builder
                        .GET(handler::listar)
                        .POST(handler::crear)
                        .GET("/fecha-fin-proyecto", handler::consultarFechaFin))
                .build();
    }
}
