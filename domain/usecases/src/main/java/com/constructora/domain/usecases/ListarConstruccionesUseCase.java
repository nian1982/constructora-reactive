package com.constructora.domain.usecases;

import org.springframework.stereotype.Service;

import com.constructora.domain.model.Construccion;
import com.constructora.domain.ports.ConstruccionRepository;

import reactor.core.publisher.Flux;

@Service
public class ListarConstruccionesUseCase {

    private final ConstruccionRepository construccionRepository;

    public ListarConstruccionesUseCase(ConstruccionRepository construccionRepository){
        this.construccionRepository = construccionRepository;
    }

    public Flux<Construccion> execute(){
        return construccionRepository.findAll();
    }

}
