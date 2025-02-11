package com.sistema_laboratorios.main.services;

import org.springframework.stereotype.Service;

import com.sistema_laboratorios.main.dto.ReservaRetornoDto;
import com.sistema_laboratorios.main.models.Horario;
import com.sistema_laboratorios.main.models.Laboratorio;
import com.sistema_laboratorios.main.models.Reserva;
import com.sistema_laboratorios.main.models.Usuario;
import com.sistema_laboratorios.main.repositories.HorarioRepository;
import com.sistema_laboratorios.main.repositories.ReservaRepository;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class ReservaServices {
    private final ReservaRepository reservaRepository;

    private final HorarioRepository horarioRepository;

    private final HorarioServices horarioServices;

    public ReservaServices(ReservaRepository reservaRepository, HorarioServices horarioServices, HorarioRepository horarioRepository) {
        this.reservaRepository = reservaRepository;
        this.horarioServices = horarioServices;
        this.horarioRepository = horarioRepository;
    }

    /* Métodos do service */

    public ReservaRetornoDto criarReservaDto(Reserva reserva){
        List<Horario> horariosReserva = this.horarioRepository.buscarHorarioPorReserva(reserva.getId());
        //Como preciso extrair o laboratório de uma reserva, sendo que todos os horários filtrados por reserva possui o mesmo laboratório, basta pegar o laboratorio da primeiro horário que me retornar
        //Verifico se o retorno de laboratório é vazio
        Laboratorio laboratorio = !horariosReserva.isEmpty() ? horariosReserva.get(0).getLaboratorioHorario() : null;

        ReservaRetornoDto reservaDto = new ReservaRetornoDto(
            reserva.getId(), 
            reserva.getDataReserva(), 
            laboratorio,
            horariosReserva
        );
        
        return reservaDto;
    }

    public Reserva buscarReservaPorId(Long idReserva){
        Optional<Reserva> reserva = this.reservaRepository.findById(idReserva);
        return reserva.orElseThrow(() -> new RuntimeException("Falha ao buscar reserva por id"));
    }

    public List<ReservaRetornoDto> buscarReservasPorUsuario(Long usuId){
        List<Reserva> reservas = this.reservaRepository.buscarReservasPorUsuario(usuId);
        List<ReservaRetornoDto> reservaDto = new ArrayList<>();
        for (Reserva reserva : reservas) {
            reservaDto.add(criarReservaDto(reserva));
        }
        return reservaDto;
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

        LocalDate dataAtual = LocalDate.now();
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
