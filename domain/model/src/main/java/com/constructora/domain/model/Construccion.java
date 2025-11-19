package com.constructora.domain.model;

import java.util.Objects;

public class Construccion {

    private Long id;
    private String nombre;

    public Construccion(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Construccion construccion = (Construccion) o;
        return Objects.equals(id, construccion.id) || Objects.equals(nombre, construccion.nombre);
    }

}
