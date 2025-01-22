package com.sistema_laboratorios.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema_laboratorios.main.models.Horario;

@Repository //Anotação que indico ao spring que é um repositório

public interface HorarioRepository extends JpaRepository<Horario, Long>{ //Preciso indicar a qual model é vinculado e qual o tipo da váriavel que é PK
    //Query para buscar horarios por laboratorio
    @Query(value = "SELECT * FROM horario WHERE laboratorio_horario = :labId", nativeQuery = true)
    List<Horario> buscarHorariosPorLaboratorio(@Param("labId") Long labId);

    //Query para buscar horarios por reserva
    @Query(value = "SELECT * FROM horario WHERE reserva_horario =:reservaId", nativeQuery = true)
    List<Horario> buscarHorarioPorReserva(@Param("reservaId") Long reservaId);
}
