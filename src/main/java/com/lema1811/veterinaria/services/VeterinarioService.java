package com.lema1811.veterinaria.services;

import com.lema1811.veterinaria.model.Veterinario;
import com.lema1811.veterinaria.repository.VeterinarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    public List<Veterinario> listarTodos() {
        return veterinarioRepository.findAll();
    }

    public Veterinario obtenerPorId(int id) {
        return veterinarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Veterinario no encontrado con id: " + id));
    }

    public Veterinario crear(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    public Veterinario actualizar(int id, Veterinario detalles) {
        Veterinario existente = obtenerPorId(id);
        existente.setNombre(detalles.getNombre());
        existente.setEspecialidad(detalles.getEspecialidad());
        existente.setAniosExperiencia(detalles.getAniosExperiencia());
        existente.setDisponible(detalles.isDisponible());
        return veterinarioRepository.save(existente);
    }

    public void eliminar(int id) {
        if (!veterinarioRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Veterinario no encontrado con id: " + id);
        }
        veterinarioRepository.deleteById(id);
    }

    // --- Situaciones especiales ---

    public List<Veterinario> buscarDisponiblesPorEspecialidad(String especialidad) {
        return veterinarioRepository.findByEspecialidadIgnoreCaseAndDisponibleTrue(especialidad);
    }

    public Veterinario obtenerMasExperimentado() {
        return veterinarioRepository.findTopByOrderByAniosExperienciaDesc()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No hay veterinarios registrados"));
    }

    public Veterinario cambiarDisponibilidad(int id, boolean valor) {
        Veterinario existente = obtenerPorId(id);
        if (existente.isDisponible() == valor) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El veterinario ya tiene disponibilidad en: " + valor);
        }
        existente.setDisponible(valor);
        return veterinarioRepository.save(existente);
    }
}