
package com.constructora.infra.entrypoints;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;     
import org.springframework.http.MediaType;
import com.constructora.domain.model.TipoConstruccion;
import com.constructora.domain.usecases.ListarTipoConstruccionUseCase;


@Component  
public class TipoConstruccionHandler {

    private final ListarTipoConstruccionUseCase listarTipoConstruccionUseCase;
    
    public TipoConstruccionHandler(ListarTipoConstruccionUseCase listarTipoConstruccionUseCase) {
        this.listarTipoConstruccionUseCase = listarTipoConstruccionUseCase;
    }   

//     public Mono<ServerResponse> crear(ServerRequest request) {
//         Mono<TipoConstruccion> tipoConstruccionMono = request.bodyToMono(TipoConstruccion.class);
//         return tipoConstruccionMono.flatMap(crearTipoConstruccionUseCase::execute)
//                 .flatMap(tipoConstruccion -> ServerResponse.ok()
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .bodyValue(tipoConstruccion))
//                 .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
//     }

    public Mono<ServerResponse> listar(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(listarTipoConstruccionUseCase.execute(), TipoConstruccion.class);
    }

//     public Mono<ServerResponse> obtener(ServerRequest request) {
//         Long id = Long.valueOf(request.pathVariable("id"));

//         return obtenerTipoConstruccionUseCase.execute(id)
//                 .flatMap(tipoConstruccion -> ServerResponse.ok()
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .bodyValue(tipoConstruccion))
//                 .onErrorResume(e -> ServerResponse.badRequest()
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .bodyValue(e.getMessage()));
//     }   

}
