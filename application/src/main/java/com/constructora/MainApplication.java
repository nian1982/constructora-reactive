package com.constructora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.constructora.domain.usecases",
    "com.constructora.infra.entrypoints",
    "com.constructora.infra.adapters.db" // Este paquete ahora contiene la clase del adaptador
})
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
