package com.constructora.infra.adapters.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("solicitudes")
public class OrdenEntity {

    @Id
    private Long id;
    @Column("coordenada_x")
    private Double coordenadaX;
    @Column("coordenada_y")
    private Double coordenadaY;
    @Column("fecha_solicitud")
    private LocalDateTime fechaSolicitud;
    @Column("fecha_inicio")
    private LocalDateTime fechaInicio;
    @Column("fecha_finalizacion")
    private LocalDateTime fechaFinalizacion;
    private String estado;
    @Column("construccion_id")
    private Long construccionId;

    public OrdenEntity() {
    }

    public OrdenEntity(Long id, Double coordenadaX, Double coordenadaY, LocalDateTime fechaSolicitud,
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

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDateTime fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getConstruccionId() {
        return construccionId;
    }

    public void setConstruccionId(Long construccionId) {
        this.construccionId = construccionId;
    }
}
