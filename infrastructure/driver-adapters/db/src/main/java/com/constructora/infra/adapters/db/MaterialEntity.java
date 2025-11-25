package com.constructora.infra.adapters.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.util.Objects;

@Table("materiales")
public class MaterialEntity {

    @Id
    private Long id;
    private String nombre;
    private String sigla;
    private Integer cantidad;

    public MaterialEntity() {}

    public MaterialEntity(String nombre, String sigla, Integer cantidad) {
        this.nombre = nombre;
        this.sigla = sigla;
        this.cantidad = cantidad;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialEntity that = (MaterialEntity) o;
        return Objects.equals(id, that.id) || Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "MaterialEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", sigla='" + sigla + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
