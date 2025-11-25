package com.constructora.infra.entrypoints;

public class OrdenRequest {
    private Double coordenadaX;
    private Double coordenadaY;
    private Long construccionId;

    public OrdenRequest() {
    }

    public OrdenRequest(Double coordenadaX, Double coordenadaY, Long construccionId) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.construccionId = construccionId;
    }

    public Double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public Long getConstruccionId() {
        return construccionId;
    }

    public void setConstruccionId(Long construccionId) {
        this.construccionId = construccionId;
    }
}
