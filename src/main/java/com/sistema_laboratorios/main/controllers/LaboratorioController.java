package com.sistema_laboratorios.main.controllers;
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

    private LaboratorioServices laboratorioServices;

    public LaboratorioController(LaboratorioServices laboratorioServices) {
        this.laboratorioServices = laboratorioServices;
    }
  
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
    
}
 

