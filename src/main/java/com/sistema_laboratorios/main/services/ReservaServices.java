package com.sistema_laboratorios.main.services;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema_laboratorios.main.models.Horario;
import com.sistema_laboratorios.main.models.Reserva;
import com.sistema_laboratorios.main.models.Usuario;
import com.sistema_laboratorios.main.repositories.ReservaRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class ReservaServices {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HorarioServices horarioServices;


    public Reserva buscarReservaPorId(Long idReserva){
        Optional<Reserva> reserva = this.reservaRepository.findById(idReserva);
        return reserva.orElseThrow(() -> new RuntimeException("Falha ao buscar reserva por id"));
    }

    @Transactional
    public Reserva criarReserva(List<Horario> horarios, Usuario usuario){
        LocalDate getDate = LocalDate.now();
        Date dataAtual = Date.valueOf(getDate);
        Reserva reserva = new Reserva(null, dataAtual, usuario);

        this.reservaRepository.save(reserva);

        for (Horario horario : horarios) {
            this.horarioServices.atualizarHorario(horario, reserva);
        }

        return reserva;
    }

    @Transactional
    public void deletarReserva(Reserva reserva){
        List<Horario> horarios = this.horarioServices.buscarHorariosPorReserva(reserva.getId());
        for (Horario horario : horarios) {
            this.horarioServices.cancelarReservaHorario(horario);
        }

        try {
            this.reservaRepository.delete(reserva);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível deletar a reserva");
        }
    }

    public ReservaServices() {
    }

    public ReservaServices(ReservaRepository reservaRepository, HorarioServices horarioServices) {
        this.reservaRepository = reservaRepository;
        this.horarioServices = horarioServices;
    }

    public ReservaRepository getReservaRepository() {
        return this.reservaRepository;
    }

    public void setReservaRepository(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public HorarioServices getHorarioServices() {
        return this.horarioServices;
    }

    public void setHorarioServices(HorarioServices horarioServices) {
        this.horarioServices = horarioServices;
    }

    public ReservaServices reservaRepository(ReservaRepository reservaRepository) {
        setReservaRepository(reservaRepository);
        return this;
    }

    public ReservaServices horarioServices(HorarioServices horarioServices) {
        setHorarioServices(horarioServices);
        return this;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservaRepository, horarioServices);
    }

    
}
