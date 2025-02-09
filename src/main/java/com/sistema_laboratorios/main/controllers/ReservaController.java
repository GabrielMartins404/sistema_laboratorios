package com.sistema_laboratorios.main.controllers;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistema_laboratorios.main.models.Horario;
import com.sistema_laboratorios.main.models.Reserva;
import com.sistema_laboratorios.main.models.Usuario;
import com.sistema_laboratorios.main.services.ReservaServices;
import com.sistema_laboratorios.main.services.Usuarioservice;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reserva")
@Validated
public class ReservaController {
    
    private final ReservaServices reservaServices;

    private final Usuarioservice usuarioservice;

    public ReservaController(ReservaServices reservaServices, Usuarioservice usuarioservice) {
        this.reservaServices = reservaServices;
        this.usuarioservice = usuarioservice;
    }

    //Buscar reservas por usuário
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Reserva>> findReserveById(@PathVariable Long idUsuario){
        List<Reserva> reserva = this.reservaServices.buscarReservasPorUsuario(idUsuario);
        return ResponseEntity.ok().body(reserva);
    }
    
    // Criar reserva 
    @PostMapping("/{idUsuario}")
    public ResponseEntity<Void> criarReserva(@RequestBody List<Horario> horarios, @PathVariable Long idUsuario){
        if(horarios == null || horarios.isEmpty()){
            throw new RuntimeException("A lista de horários não pode ser vazia");
        }

        Usuario usuario = this.usuarioservice.buscarUsuarioPorId(idUsuario);
        Reserva reserva = this.reservaServices.criarReserva(horarios, usuario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idReserva}").buildAndExpand(reserva.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //Cancelar reserva
    @DeleteMapping("/{idReserva}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long idReserva){
        Reserva reserva = this.reservaServices.buscarReservaPorId(idReserva);
        this.reservaServices.deletarReserva(reserva);
        return ResponseEntity.noContent().build();
    }
}
