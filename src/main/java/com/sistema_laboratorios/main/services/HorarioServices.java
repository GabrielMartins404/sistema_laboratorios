package com.sistema_laboratorios.main.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.sistema_laboratorios.main.models.Horario;
import com.sistema_laboratorios.main.models.Reserva;
import com.sistema_laboratorios.main.repositories.HorarioRepository;

import jakarta.transaction.Transactional;

@Service
public class HorarioServices {
    
    private final HorarioRepository horarioRepository;

    //A inserção por construto permite que meus repositorios sejam imutáveis, garantindo um melhor encapsulamento
    public HorarioServices(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    /* Métodos do services */

    public Horario buscarHorarioPorId(Long idHorario){
        Optional<Horario> horario = this.horarioRepository.findById(idHorario);
        return horario.orElseThrow(() -> new RuntimeException("Não foi possível buscar o horário pelo ID específicado"));
    }

    public List<Horario> buscarHorariosPorLab(Long idLab){
        List<Horario> horarios = this.horarioRepository.buscarHorariosPorLaboratorio(idLab);
        return horarios;
    }

    public List<Horario> buscarHorariosPorReserva(Long idReserva){
        List<Horario> horarios = this.horarioRepository.buscarHorarioPorReserva(idReserva);
        return horarios;
    }

    //Função tem como propósito alterar o horário, inserindo a reserva e alterando a disponibilidade falso
    @Transactional
    public Horario atualizarHorario(Horario horario, Reserva reserva){
        Horario newHorario = horario;

        if(horario.getDisponivel()){
            newHorario.setDisponivel(false);
            newHorario.setReservaHorario(reserva);
        }else{
            new RuntimeException("Horário não está disponível");
        }

        this.horarioRepository.save(newHorario);
        return newHorario;
    }

    //Função tem como propósito alterar o horário, retirando a reserva e alterando a disponibilidade para verdadeiro
    @Transactional
    public void cancelarReservaHorario(Horario horario){
        Horario newHorario = horario;

        if(horario.getDisponivel()){
            new RuntimeException("Horário não tem nenhuma reserva vinculada");
        }else{
            newHorario.setDisponivel(true);
            newHorario.setReservaHorario(null);
        }

        this.horarioRepository.save(newHorario);
    }
}
