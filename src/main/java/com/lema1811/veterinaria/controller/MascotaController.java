package com.lema1811.veterinaria.controller;

import com.lema1811.veterinaria.model.Mascota;
import com.lema1811.veterinaria.model.Medicamento;
import com.lema1811.veterinaria.services.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mascota")
public class MascotaController {
    private MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService){
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public List<Mascota> verTodos(){
        return mascotaService.traerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> traerUno(@PathVariable int id){
        return mascotaService.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mascota> guardar(@RequestBody Mascota mascota){
        Mascota nuevaMascota = mascotaService.guardar(mascota);
        return new ResponseEntity<>(nuevaMascota, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> editar(@PathVariable int id, @RequestBody Mascota mascota){
        return mascotaService.editar(id, mascota)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Mascota> eliminar (@PathVariable int id){
        if (mascotaService.eliminar(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<Mascota>> buscarPorEspecie(@PathVariable String especie) {
        return ResponseEntity.ok(mascotaService.buscarPorEspecie(especie));
    }

    @GetMapping("/conteo-especies")
    public ResponseEntity<Map<String, Long>> contarPorEspecie() {
        return ResponseEntity.ok(mascotaService.contarPorEspecie());
    }

}
