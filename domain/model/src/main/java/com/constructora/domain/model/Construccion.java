package com.constructora.domain.model;

import java.util.Objects;

public class Construccion {

    private Long id;
    private String nombre;
    private int dias;

    public Construccion(Long id, String nombre, int dias) {
        this.id = id;
        this.nombre = nombre;
        this.dias = dias;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDias() {
        return dias;
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
