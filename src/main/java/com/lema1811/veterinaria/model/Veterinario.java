package com.lema1811.veterinaria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Veterinario {
    @Id
    private int id;
    private String nombre;
    private String especialidad;
    private int aniosExperiencia;
    private boolean disponible;
}
