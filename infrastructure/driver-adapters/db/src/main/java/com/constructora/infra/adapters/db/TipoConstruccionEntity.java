package com.constructora.infra.adapters.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("tipo_construccion")
public class TipoConstruccionEntity {

    @Id
    private Long id;

    private Integer cantidad;

    @Column("construccion_id")
    private Long construccionId;

    @Column("material_id")
    private Long materialId;

    public TipoConstruccionEntity() {
    }

    public TipoConstruccionEntity(Long id, Integer cantidad, Long construccionId, Long materialId) {
        this.id = id;
        this.cantidad = cantidad;
        this.construccionId = construccionId;
        this.materialId = materialId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getConstruccionId() {
        return construccionId;
    }

    public void setConstruccionId(Long construccionId) {
        this.construccionId = construccionId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

}
