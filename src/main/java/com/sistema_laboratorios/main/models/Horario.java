package com.sistema_laboratorios.main.models;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = Horario.TABLE_NAME)
public class Horario {
    public static final String TABLE_NAME = "horario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "horaInicio", nullable = false)
    private Time horaInicio;

    @Column(name = "horaFim", nullable = false)
    private Time horaFim;

    @Column(name = "diaSemana", nullable = false)
    private String diaSemana;

    @Column(name = "disponivel", columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean disponivel = true;

    /* Anotações das chaves estrangeiras */

    //A lógica é que vários horários podem estar vinculados a um unico laboratório
    @ManyToOne  //Anotação onde indico que há muitos horários para 1 só láboratório
    @JoinColumn(name = "laboratorioHorario", nullable = false)
    private Laboratorio laboratorioHorario;

    //Mesma lógica acima. Um horário pode ter somente uma reserva, contudo, uma reserva pode ter vários horários
    @ManyToOne
    @JoinColumn(name = "reservaHorario", nullable = true) //Como nem todos os horários terão reserva, a mesma poderá ser true
    private Reserva reservaHorario;

    
    public Horario() {
    }

    public Horario(Long id, Time horaInicio, Time horaFim, String diaSemana, boolean disponivel, Laboratorio laboratorioHorario, Reserva reservaHorario) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaSemana = diaSemana;
        this.disponivel = disponivel;
        this.laboratorioHorario = laboratorioHorario;
        this.reservaHorario = reservaHorario;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getHoraInicio() {
        return this.horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFim() {
        return this.horaFim;
    }

    public void setHoraFim(Time horaFim) {
        this.horaFim = horaFim;
    }

    public String getDiaSemana() {
        return this.diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public boolean getDisponivel() {
        return this.disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Laboratorio getLaboratorioHorario() {
        return this.laboratorioHorario;
    }

    public void setLaboratorioHorario(Laboratorio laboratorioHorario) {
        this.laboratorioHorario = laboratorioHorario;
    }

    public Reserva getReservaHorario() {
        return this.reservaHorario;
    }

    public void setReservaHorario(Reserva reservaHorario) {
        this.reservaHorario = reservaHorario;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, horaInicio, horaFim, disponivel, laboratorioHorario, reservaHorario);
    }


}