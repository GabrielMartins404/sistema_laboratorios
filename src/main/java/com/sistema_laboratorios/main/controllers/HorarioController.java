package com.sistema_laboratorios.main.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sistema_laboratorios.main.models.Horario;
import com.sistema_laboratorios.main.models.Reserva;
import com.sistema_laboratorios.main.services.HorarioServices;
import com.sistema_laboratorios.main.services.ReservaServices;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/horarios")
@Validated
public class HorarioController {
    private final HorarioServices horarioServices;

    private final ReservaServices reservaServices;

    public HorarioController(HorarioServices horarioServices, ReservaServices reservaServices) {
        this.horarioServices = horarioServices;
        this.reservaServices = reservaServices;
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

    //Atualização para inserir reserva no horário
    //Verifica se vai ser necessário
    @PutMapping("/{idReserva}")
    public ResponseEntity<Void> atualizarReservaHorario(@RequestBody List<Horario> horarios, @PathVariable Long idReserva){
        Reserva reserva = this.reservaServices.buscarReservaPorId(idReserva);

        for (Horario horario : horarios) {
            this.horarioServices.atualizarHorario(horario, reserva);
        }
        return ResponseEntity.noContent().build();
    }

}
