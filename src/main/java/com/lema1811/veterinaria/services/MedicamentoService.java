package com.lema1811.veterinaria.services;

import com.lema1811.veterinaria.model.Medicamento;
import com.lema1811.veterinaria.repository.MedicamentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    private MedicamentoRepository medicamentoRepository;

    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    //TRAER TODOS
    public List<Medicamento> traerTodos(){
        return medicamentoRepository.findAll();
    }

    //TRAER UNO
    public Optional<Medicamento> traerUno(int id){
        return medicamentoRepository.findById(id);
    }

    //GUARDAR
    public Medicamento  guardar (Medicamento medicamento){
        return medicamentoRepository.save(medicamento);
    }

    //EDITAR
    public Optional <Medicamento> editar(int id, Medicamento medicamentoActualizado){
        return medicamentoRepository.findById(id)
                .map( medExiste ->{
                    medExiste.setNombre(medicamentoActualizado.getNombre());
                    medExiste.setPrecio(medicamentoActualizado.getPrecio());

                    return medicamentoRepository.save(medExiste);
                });
    }

    //ELIMINAR
    public boolean eliminar (int id){
        if ( medicamentoRepository.existsById(id)){
            medicamentoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    //STOCK BAJO
    public List<Medicamento> buscarStockBajo(int minimo) {
        return medicamentoRepository.findByStockLessThan(minimo);
    }

    //VENTA LIBRE
    public List<Medicamento> buscarVentaLibre() {
        return medicamentoRepository.findByRequiereRecetaFalse();
    }

    //DESPACHAR MEDICINAS
    public Medicamento despachar(int id, int cantidad) {
        if (cantidad <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La cantidad a despachar debe ser mayor a 0");
        }

        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Medicamento no encontrado con id: " + id));

        if (medicamento.getStock() < cantidad) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Stock insuficiente. Disponible: " + medicamento.getStock());
        }

        medicamento.setStock(medicamento.getStock() - cantidad);
        return medicamentoRepository.save(medicamento);
    }
}