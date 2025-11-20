package com.constructora.infra.adapters.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.util.Objects;

// Esta clase vive en infraestructura y puede tener anotaciones del framework.
@Table("construcciones")
public class ConstruccionEntity {

    @Id
    private Long id;
    private String nombre;
    private int dias;

    public ConstruccionEntity() {
    }

    public ConstruccionEntity(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ConstruccionEntity that = (ConstruccionEntity) o;
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
                ", dias='" + dias + '\'' +
                '}';
    }

}
