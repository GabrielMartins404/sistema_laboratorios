package com.sistema_laboratorios.main.models;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "data", nullable = false)
    private Date data;
    
    @Column(name = "disponivel", columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean disponivel = true;

    // @Column(name = "laboratorio", nullable = false)
    // private Laboratorio laboratorio;

    public Horario() {
    }

    public Horario(Long id, Time horaInicio, Time horaFim, Date data, boolean disponivel) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.data = data;
        this.disponivel = disponivel;
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

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isDisponivel() {
        return this.disponivel;
    }

    public boolean getDisponivel() {
        return this.disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Horario id(Long id) {
        setId(id);
        return this;
    }

    public Horario horaInicio(Time horaInicio) {
        setHoraInicio(horaInicio);
        return this;
    }

    public Horario horaFim(Time horaFim) {
        setHoraFim(horaFim);
        return this;
    }

    public Horario data(Date data) {
        setData(data);
        return this;
    }

    public Horario disponivel(boolean disponivel) {
        setDisponivel(disponivel);
        return this;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, horaInicio, horaFim, data, disponivel);
    }

}
