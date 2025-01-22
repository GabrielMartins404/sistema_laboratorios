package com.sistema_laboratorios.main.models;

import java.sql.Date;
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

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    /* Anotações das chaves estrangeiras */

    //A lógica é que vários horários podem estar vinculados a um unico laboratório
    @ManyToOne  //Anotação onde indico que há muitos horários para 1 só láboratório
    @JoinColumn(name = "laboratorioHorario", nullable = false)
    @JsonBackReference //Essa anotação é de suma importância pois ela não permite a inserção de um loop quando é solicitado o Json dos laboratórios. Sem ela, ocorrerá um loop infinito quando tentarmos visualizar os laboratórios
    private Laboratorio laboratorioHorario;

    //Mesma lógica acima. Um horário pode ter somente uma reserva, contudo, uma reserva pode ter vários horários
    @ManyToOne
    @JoinColumn(name = "reservaHorario", nullable = true) //Como nem todos os horários terão reserva, a mesma poderá ser true
    @JsonBackReference
    private Reserva reservaHorario;

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
