package com.sistema_laboratorios.main.controllers;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sistema_laboratorios.main.models.Laboratorio;
import com.sistema_laboratorios.main.services.LaboratorioServices;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/laboratorio")
@Validated
public class LaboratorioController {
    @Autowired
    private LaboratorioServices laboratorioServices;
  
    @GetMapping("/")
    public ResponseEntity<List<Laboratorio>> buscarTodosOsLaboratorios(){
        List<Laboratorio> laboratorios = this.laboratorioServices.buscarTodosLaboratorios();
         return ResponseEntity.ok().body(laboratorios);
    }
    
    @GetMapping("/{idLaboratorio}")
    public ResponseEntity<Laboratorio> buscarLaboratorioPorId(@PathVariable Long idLaboratorio){
        Laboratorio laboratorio = this.laboratorioServices.buscarLaboratorioPorId(idLaboratorio);
        return ResponseEntity.ok().body(laboratorio);
    }
    
    public LaboratorioController() {
    }

    public LaboratorioController(LaboratorioServices laboratorioServices) {
        this.laboratorioServices = laboratorioServices;
    }

    public LaboratorioServices getLaboratorioServices() {
        return this.laboratorioServices;
    }

    public void setLaboratorioServices(LaboratorioServices laboratorioServices) {
        this.laboratorioServices = laboratorioServices;
    }

    public LaboratorioController laboratorioServices(LaboratorioServices laboratorioServices) {
        setLaboratorioServices(laboratorioServices);
        return this;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }
    
}
 

