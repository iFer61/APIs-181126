package com.lema1811.veterinaria.repository;

import com.lema1811.veterinaria.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository <Medicamento, Integer>{
    List<Medicamento> findByStockLessThan(int minimo);
    List<Medicamento> findByRequiereRecetaFalse();
}
