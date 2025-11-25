package com.constructora.config;

import com.constructora.domain.ports.ConstruccionRepository;
import com.constructora.domain.ports.MaterialRepository;
import com.constructora.domain.ports.OrdenRepository;
import com.constructora.domain.ports.TipoConstruccionRepository;
import com.constructora.domain.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CrearMaterialUseCase crearMaterialUseCase(MaterialRepository materialRepository) {
        return new CrearMaterialUseCase(materialRepository);
    }

    @Bean
    public ListarConstruccionesUseCase listarConstruccionesUseCase(ConstruccionRepository construccionRepository) {
        return new ListarConstruccionesUseCase(construccionRepository);
    }

    @Bean
    public ListarMaterialesUseCase listarMaterialesUseCase(MaterialRepository materialRepository) {
        return new ListarMaterialesUseCase(materialRepository);
    }

    @Bean
    public ListarTipoConstruccionUseCase listarTipoConstruccionUseCase(
            TipoConstruccionRepository tipoConstruccionRepository) {
        return new ListarTipoConstruccionUseCase(tipoConstruccionRepository);
    }

    @Bean
    public ListarTipoConstrucionUseCase listarTipoConstrucionUseCase(
            TipoConstruccionRepository tipoConstruccionRepository) {
        return new ListarTipoConstrucionUseCase(tipoConstruccionRepository);
    }

    @Bean
    public ObtenerConstruccionUseCase obtenerConstruccionUseCase(ConstruccionRepository construccionRepository) {
        return new ObtenerConstruccionUseCase(construccionRepository);
    }

    @Bean
    public ObtenerMaterialUseCase obtenerMaterialUseCase(MaterialRepository materialRepository) {
        return new ObtenerMaterialUseCase(materialRepository);
    }

    @Bean
    public CrearOrdenUseCase crearOrdenUseCase(OrdenRepository ordenRepository,
            TipoConstruccionRepository tipoConstruccionRepository,
            MaterialRepository materialRepository,
            ConstruccionRepository construccionRepository) {
        return new CrearOrdenUseCase(ordenRepository, tipoConstruccionRepository, materialRepository,
                construccionRepository);
    }

    @Bean
    public ConsultarFechaFinProyectoUseCase consultarFechaFinProyectoUseCase(OrdenRepository ordenRepository) {
        return new ConsultarFechaFinProyectoUseCase(ordenRepository);
    }

    @Bean
    public ListarOrdenesUseCase listarOrdenesUseCase(OrdenRepository ordenRepository) {
        return new ListarOrdenesUseCase(ordenRepository);
    }
}
