package com.sistema_laboratorios.main.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sistema_laboratorios.main.models.Horario;
import com.sistema_laboratorios.main.models.Laboratorio;

public class ReservaRetornoDto {
        private Long id;
    
        private LocalDate dataReserva;

        private Laboratorio laboratorio;
        
        private List<Horario> horarios = new ArrayList();


    public ReservaRetornoDto() {
    }

    public ReservaRetornoDto(Long id, LocalDate dataReserva, Laboratorio laboratorio, List<Horario> horarios) {
        this.id = id;
        this.dataReserva = dataReserva;
        this.laboratorio = laboratorio;
        this.horarios = horarios;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataReserva() {
        return this.dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Laboratorio getLaboratorio() {
        return this.laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public List<Horario> getHorarios() {
        return this.horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public ReservaRetornoDto id(Long id) {
        setId(id);
        return this;
    }

    public ReservaRetornoDto dataReserva(LocalDate dataReserva) {
        setDataReserva(dataReserva);
        return this;
    }

    public ReservaRetornoDto laboratorio(Laboratorio laboratorio) {
        setLaboratorio(laboratorio);
        return this;
    }

    public ReservaRetornoDto horarios(List<Horario> horarios) {
        setHorarios(horarios);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", dataReserva='" + getDataReserva() + "'" +
            ", laboratorio='" + getLaboratorio() + "'" +
            ", horarios='" + getHorarios() + "'" +
            "}";
    }
    
    
}
