package com.sistema_laboratorios.main.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = Reserva.TABLE_NAME)
public class Reserva{
    
    public static final String TABLE_NAME = "reserva"; 

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dataReserva", nullable = false)
    @NotNull
    private LocalDate dataReserva;
    
    /* Anotações das chaves estrangeiras do banco */

    //Várias reservar podem ser feitas por um só usuário
    @ManyToOne
    @JoinColumn(name = "usuarioReserva", nullable = false) //Aqui indico que ele deve criar uma coluna que será essa chave estrangeira
    private Usuario usuarioReserva;

    //Uma unica reserva pode conter vários horários. Desse modo, é preciso fazer a anotação abaixo para indicar o array de horarios que a reserva terá
    @OneToMany(mappedBy = "reservaHorario") //Indico qual coluna do Banco de dados da tabela horário a mesma será vinculada
    @JsonBackReference //Uso essa anotação para evitar uma visualização de loop infinito quando solicitar os horários vinculados a uma reserva 
    private List<Horario> horarios = new ArrayList<Horario>();


    public Reserva() {
    }

    public Reserva(Long id, LocalDate dataReserva, Usuario usuarioReserva) {
        this.id = id;
        this.dataReserva = dataReserva;
        this.usuarioReserva = usuarioReserva;
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

    public Usuario getUsuarioReserva() {
        return this.usuarioReserva;
    }

    public void setUsuarioReserva(Usuario usuarioReserva) {
        this.usuarioReserva = usuarioReserva;
    }

    public List<Horario> getHorarios() {
        return this.horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataReserva, usuarioReserva, horarios);
    }

}
