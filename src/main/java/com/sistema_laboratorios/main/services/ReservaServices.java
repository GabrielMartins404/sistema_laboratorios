package com.sistema_laboratorios.main.services;

import org.springframework.stereotype.Service;

import com.sistema_laboratorios.main.models.Horario;
import com.sistema_laboratorios.main.models.Reserva;
import com.sistema_laboratorios.main.models.Usuario;
import com.sistema_laboratorios.main.repositories.ReservaRepository;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class ReservaServices {
    private ReservaRepository reservaRepository;

    private HorarioServices horarioServices;

    public ReservaServices(ReservaRepository reservaRepository, HorarioServices horarioServices) {
        this.reservaRepository = reservaRepository;
        this.horarioServices = horarioServices;
    }

    /* Métodos do service */

    public Reserva buscarReservaPorId(Long idReserva){
        Optional<Reserva> reserva = this.reservaRepository.findById(idReserva);
        return reserva.orElseThrow(() -> new RuntimeException("Falha ao buscar reserva por id"));
    }

    @Transactional
    public Reserva criarReserva(List<Horario> horarios, Usuario usuario){
        List<Horario> horariosDisponiveis = new ArrayList<Horario>();

        //Esse for tem como proposito, verificar se os horários estão disponíveis antes de gerar a reserva
        for (Horario horario : horarios) {
            if(this.horarioServices.verificarDisponibilidadeHorario(horario)){
                horariosDisponiveis.add(horario);
            }
        }

        LocalDate getDate = LocalDate.now();
        Date dataAtual = Date.valueOf(getDate);
        Reserva reserva = new Reserva(null, dataAtual, usuario);

        this.reservaRepository.save(reserva);

        for (Horario horario : horariosDisponiveis) {
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
    
}
