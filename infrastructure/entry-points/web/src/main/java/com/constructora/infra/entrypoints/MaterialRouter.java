package com.constructora.infra.entrypoints;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MaterialRouter {

    @Bean
    public RouterFunction<ServerResponse> rutasMateriales(MaterialHandler handler) {
        return route()
                .path("/api/materiales", builder -> builder
                        .POST(handler::crear)
                        .GET("/{id}", handler::obtener)
                        .GET(handler::listar))
                .build();
    }
}