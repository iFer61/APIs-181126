package com.lema1811.veterinaria.repository;

import com.lema1811.veterinaria.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository <Medicamento, Integer>{
}
