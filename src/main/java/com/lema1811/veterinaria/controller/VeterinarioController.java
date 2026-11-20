package com.lema1811.veterinaria.controller;

import com.lema1811.veterinaria.model.Veterinario;
import com.lema1811.veterinaria.services.VeterinarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinario")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    // --- CRUD básico ---

    @GetMapping
    public ResponseEntity<List<Veterinario>> listarTodos() {
        return ResponseEntity.ok(veterinarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterinario> obtenerPorId(@PathVariable int id) {
        return ResponseEntity.ok(veterinarioService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Veterinario> crear(@RequestBody Veterinario veterinario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(veterinarioService.crear(veterinario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veterinario> actualizar(@PathVariable int id, @RequestBody Veterinario veterinario) {
        return ResponseEntity.ok(veterinarioService.actualizar(id, veterinario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        veterinarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // --- Situaciones especiales ---

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<Veterinario>> buscarPorEspecialidad(@PathVariable String especialidad) {
        return ResponseEntity.ok(veterinarioService.buscarDisponiblesPorEspecialidad(especialidad));
    }

    @GetMapping("/mas-experimentado")
    public ResponseEntity<Veterinario> obtenerMasExperimentado() {
        return ResponseEntity.ok(veterinarioService.obtenerMasExperimentado());
    }

    @PutMapping("/{id}/disponibilidad")
    public ResponseEntity<Veterinario> cambiarDisponibilidad(
            @PathVariable int id,
            @RequestParam boolean valor) {
        return ResponseEntity.ok(veterinarioService.cambiarDisponibilidad(id, valor));
    }
}