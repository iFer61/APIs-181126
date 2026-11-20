package com.lema1811.veterinaria.repository;

import com.lema1811.veterinaria.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VeterinarioRepository extends JpaRepository <Veterinario, Integer>{
    // Veterinarios de una especialidad (sin importar mayúsculas) que estén disponibles
    List<Veterinario> findByEspecialidadIgnoreCaseAndDisponibleTrue(String especialidad);

    // El veterinario con más años de experiencia
    Optional<Veterinario> findTopByOrderByAniosExperienciaDesc();
}
