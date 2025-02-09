package com.sistema_laboratorios.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema_laboratorios.main.models.Horario;
import com.sistema_laboratorios.main.models.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{

    //Query para buscar reservas por usuário
    @Query(value = "SELECT * FROM reserva WHERE usuario_reserva = :usuId", nativeQuery = true)
    List<Reserva> buscarReservasPorUsuario(@Param("usuId") Long usuId);
}
