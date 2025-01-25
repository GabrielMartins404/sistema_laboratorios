package com.sistema_laboratorios.main.services;
import org.springframework.stereotype.Service;
import com.sistema_laboratorios.main.models.Laboratorio;
import com.sistema_laboratorios.main.repositories.LaboratorioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioServices {
    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioServices(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    /* Métodos do service */

    public Laboratorio buscarLaboratorioPorId(Long id){
        Optional<Laboratorio> laboratorio = this.laboratorioRepository.findById(id);
        return laboratorio.orElseThrow(() -> new RuntimeException("Laboratorio nao encotrado"));  
    }
    public List<Laboratorio> buscarTodosLaboratorios(){
        List<Laboratorio> laboratorio = this.laboratorioRepository.findAll();
        return laboratorio;

    }
    
}
