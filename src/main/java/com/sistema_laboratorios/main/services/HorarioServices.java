package com.sistema_laboratorios.main.services;

import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistema_laboratorios.main.models.Horario;
import com.sistema_laboratorios.main.models.Reserva;
import com.sistema_laboratorios.main.repositories.HorarioRepository;

import jakarta.transaction.Transactional;

@Service
public class HorarioServices {
    
    @Autowired
    private HorarioRepository horarioRepository;


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


    public HorarioServices() {
    }

    public HorarioServices(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public HorarioRepository getHorarioRepository() {
        return this.horarioRepository;
    }

    public void setHorarioRepository(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public HorarioServices horarioRepository(HorarioRepository horarioRepository) {
        setHorarioRepository(horarioRepository);
        return this;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }
    
}
