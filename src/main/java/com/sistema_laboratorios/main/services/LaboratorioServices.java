package com.sistema_laboratorios.main.services;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistema_laboratorios.main.models.Laboratorio;
import com.sistema_laboratorios.main.repositories.LaboratorioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioServices {
    @Autowired
    private LaboratorioRepository laboratorioRepository;

    public Laboratorio buscarLaboratorioPorId(Long id){
        Optional<Laboratorio> laboratorio = this.laboratorioRepository.findById(id);
        return laboratorio.orElseThrow(() -> new RuntimeException("Laboratorio nao encotrado"));  
    }
    public List<Laboratorio> buscarTodosLaboratorios(){
        List<Laboratorio> laboratorio = this.laboratorioRepository.findAll();
        return laboratorio;

    }

    public LaboratorioServices() {
    }

    public LaboratorioServices(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    public LaboratorioRepository getLaboratorioRepository() {
        return this.laboratorioRepository;
    }

    public void setLaboratorioRepository(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    public LaboratorioServices laboratorioRepository(LaboratorioRepository laboratorioRepository) {
        setLaboratorioRepository(laboratorioRepository);
        return this;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }
}
