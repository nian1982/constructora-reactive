package com.constructora.domain.model;

import java.util.Objects;

public class Material {
    private Long id;
    private String nombre;
    private String sigla;
    private Integer cantidad;

    // Constructor vacío
    public Material() {
    }

    // Constructor con campos (excepto id que es auto-generado)
    public Material(String nombre, String sigla, Integer cantidad) {
        this.nombre = nombre;
        this.sigla = sigla;
        this.cantidad = cantidad;
    }

    // Getters y Setters
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    // equals y hashCode basados en el id o en un campo único como nombre
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Material material = (Material) o;
        return Objects.equals(id, material.id) || Objects.equals(nombre, material.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", sigla='" + sigla + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
