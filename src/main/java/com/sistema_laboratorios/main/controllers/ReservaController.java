package com.sistema_laboratorios.main.controllers;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import java.util.Objects;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    private ReservaServices reservaServices;

    @Autowired
    private Usuarioservice usuarioservice;

    @GetMapping("/{idReserva}")
    public ResponseEntity<Reserva> findReserveById(@PathVariable Long idReserve){
        Reserva reserva = this.reservaServices.buscarReservaPorId(idReserve);
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

    public ReservaController() {
    }

    public ReservaController(ReservaServices reservaServices, Usuarioservice usuarioservice) {
        this.reservaServices = reservaServices;
        this.usuarioservice = usuarioservice;
    }

    public ReservaServices getReservaServices() {
        return this.reservaServices;
    }

    public void setReservaServices(ReservaServices reservaServices) {
        this.reservaServices = reservaServices;
    }

    public Usuarioservice getUsuarioservice() {
        return this.usuarioservice;
    }

    public void setUsuarioservice(Usuarioservice usuarioservice) {
        this.usuarioservice = usuarioservice;
    }

    public ReservaController reservaServices(ReservaServices reservaServices) {
        setReservaServices(reservaServices);
        return this;
    }

    public ReservaController usuarioservice(Usuarioservice usuarioservice) {
        setUsuarioservice(usuarioservice);
        return this;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservaServices, usuarioservice);
    }
}
