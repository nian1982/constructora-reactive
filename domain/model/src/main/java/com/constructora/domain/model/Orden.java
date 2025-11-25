package com.constructora.domain.model;

import java.time.LocalDateTime;

public class Orden {
    private Long id;
    private Double coordenadaX;
    private Double coordenadaY;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinalizacion;
    private String estado;
    private Long construccionId;

    public Orden() {
    }

    public Orden(Long id, Double coordenadaX, Double coordenadaY, LocalDateTime fechaSolicitud,
            LocalDateTime fechaInicio, LocalDateTime fechaFinalizacion, String estado, Long construccionId) {
        this.id = id;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.estado = estado;
        this.construccionId = construccionId;
    }

    public Long getId() {
        return id;
    }

    public Double getCoordenadaX() {
        return coordenadaX;
    }

    public Double getCoordenadaY() {
        return coordenadaY;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public String getEstado() {
        return estado;
    }

    public Long getConstruccionId() {
        return construccionId;
    }

    public Orden withId(Long id) {
        return new Orden(id, this.coordenadaX, this.coordenadaY, this.fechaSolicitud, this.fechaInicio,
                this.fechaFinalizacion, this.estado, this.construccionId);
    }

    public Orden withFechas(LocalDateTime fechaInicio, LocalDateTime fechaFinalizacion) {
        return new Orden(this.id, this.coordenadaX, this.coordenadaY, this.fechaSolicitud, fechaInicio,
                fechaFinalizacion, this.estado, this.construccionId);
    }

    public Orden withEstado(String estado) {
        return new Orden(this.id, this.coordenadaX, this.coordenadaY, this.fechaSolicitud, this.fechaInicio,
                this.fechaFinalizacion, estado, this.construccionId);
    }
}
