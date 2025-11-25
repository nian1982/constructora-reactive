package com.constructora.domain.model;

import java.util.Map;

public class ConstruccionReporte {
    private long pendientes;
    private long enProgreso;
    private Map<String, Long> terminadasPorTipo;

    public ConstruccionReporte(long pendientes, long enProgreso, Map<String, Long> terminadasPorTipo) {
        this.pendientes = pendientes;
        this.enProgreso = enProgreso;
        this.terminadasPorTipo = terminadasPorTipo;
    }

    public long getPendientes() {
        return pendientes;
    }

    public long getEnProgreso() {
        return enProgreso;
    }

    public Map<String, Long> getTerminadasPorTipo() {
        return terminadasPorTipo;
    }
}
