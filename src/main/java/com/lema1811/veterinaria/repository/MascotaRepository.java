package com.lema1811.veterinaria.repository;
import com.lema1811.veterinaria.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MascotaRepository extends JpaRepository <Mascota, Integer>{
    List<Mascota> findByEspecieIgnoreCase(String especie);
}
