package com.constructora.domain.model;

public class TipoConstruccion {

    private Long id;
    private Integer cantidad;

    private Construccion construccion;

    private Material material;

    public TipoConstruccion(Long id, Integer cantidad, Construccion construccion, Material material) {
        this.id = id;
        this.cantidad = cantidad;
        this.construccion = construccion;
        this.material = material;
    }   

}
