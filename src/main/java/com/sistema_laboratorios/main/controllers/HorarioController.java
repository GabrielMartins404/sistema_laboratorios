package com.sistema_laboratorios.main.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sistema_laboratorios.main.models.Horario;
import com.sistema_laboratorios.main.services.HorarioServices;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/horarios")
@Validated
public class HorarioController {
    private final HorarioServices horarioServices;

    public HorarioController(HorarioServices horarioServices) {
        this.horarioServices = horarioServices;
    }

    @GetMapping("/lab/{idLab}")
    public ResponseEntity<List<Horario>> buscarHorariosPorLab(@PathVariable Long idLab){
        List<Horario> horarios = this.horarioServices.buscarHorariosPorLab(idLab);
        return ResponseEntity.ok().body(horarios);
    }

    @GetMapping("/reserva/{idReserva}")
    public ResponseEntity<List<Horario>> buscarHorariosPorReserva(@PathVariable Long idReserva){
        List<Horario> horarios = this.horarioServices.buscarHorariosPorReserva(idReserva);
        return ResponseEntity.ok().body(horarios);
    }

}
