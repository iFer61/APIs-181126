package com.lema1811.veterinaria.controller;

import com.lema1811.veterinaria.model.Medicamento;
import com.lema1811.veterinaria.services.MedicamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {
    private MedicamentoService medicamentoService;

    public MedicamentoController( MedicamentoService medicamentoService){
        this.medicamentoService= medicamentoService;
    }

    @GetMapping
    public List<Medicamento> verTodos(){
        return medicamentoService.traerTodos();
    }

    //TRAER UNO
    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> traerUno(@PathVariable int id){
        return medicamentoService.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //GUARDAR
    @PostMapping
    public ResponseEntity<Medicamento> guardar(@RequestBody  Medicamento medicamento){
        Medicamento nuevoMedicamento = medicamentoService.guardar(medicamento);
        return new ResponseEntity<>(nuevoMedicamento, HttpStatus.CREATED);
    }

    //EDITAR
    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> editar(@PathVariable int id, @RequestBody Medicamento medicamento){
        return medicamentoService.editar(id, medicamento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Medicamento> eliminar (@PathVariable int id){
        if (medicamentoService.eliminar(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
