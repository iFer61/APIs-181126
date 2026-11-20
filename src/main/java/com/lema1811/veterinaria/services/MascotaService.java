package com.lema1811.veterinaria.services;


import com.lema1811.veterinaria.model.Mascota;
import com.lema1811.veterinaria.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MascotaService {
    private MascotaRepository mascotaRepository;

    public MascotaService(MascotaRepository mascotaRepository){
        this.mascotaRepository = mascotaRepository;
    }
    // TRAER TODOS
    public List<Mascota> traerTodos(){
        return mascotaRepository.findAll();
    }

    // TRAER UNO
    public Optional<Mascota> traerUno(int id){
        return mascotaRepository.findById(id);
    }

    //GUARDAR
    public Mascota guardar(Mascota mascota){
        return mascotaRepository.save(mascota);
    }

    //EDITAR
    public Optional <Mascota> editar(int id, Mascota mascotaActualizada){
        return mascotaRepository.findById(id)
                .map(mascotaExiste ->{
                    mascotaExiste.setEdad(mascotaActualizada.getEdad());
                    mascotaExiste.setId(mascotaActualizada.getId());

                    return mascotaRepository.save(mascotaExiste);
                });
    }

    //ELIMINAR
    public boolean eliminar (int id){
        if ( mascotaRepository.existsById(id)){
            mascotaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    public List<Mascota> buscarPorEspecie(String especie) {
        return mascotaRepository.findByEspecieIgnoreCase(especie);
    }

    public Map<String, Long> contarPorEspecie() {
        return mascotaRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        m -> m.getEspecie() != null ? m.getEspecie().toLowerCase() : "sin_especie",
                        Collectors.counting()
                ));
    }

}
