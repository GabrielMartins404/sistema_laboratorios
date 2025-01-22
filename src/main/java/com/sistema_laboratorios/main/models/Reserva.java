package com.sistema_laboratorios.main.models;

import java.sql.Date;
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
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    private Date dataReserva;
    
    /* Anotações das chaves estrangeiras do banco */

    //Várias reservar podem ser feitas por um só usuário
    @ManyToOne
    @JoinColumn(name = "usuarioReserva", nullable = false) //Aqui indico que ele deve criar uma coluna que será essa chave estrangeira
    @JsonBackReference
    private Usuario usuarioReserva;

    //Uma unica reserva pode conter vários horários. Desse modo, é preciso fazer a anotação abaixo para indicar o array de horarios que a reserva terá
    @OneToMany(mappedBy = "reservaHorario") //Indico qual coluna do Banco de dados da tabela horário a mesma será vinculada
    @JsonManagedReference //Uso essa anotação para evitar uma visualização de loop infinito quando solicitar os horários vinculados a uma reserva 
    private List<Horario> horarios = new ArrayList<Horario>();

    public Reserva() {
    }

    public Reserva(long id, Date dataReserva) {
        this.id = id;
        this.dataReserva = dataReserva;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataReserva() {
        return this.dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Reserva id(long id) {
        setId(id);
        return this;
    }

    public Reserva dataReserva(Date dataReserva) {
        setDataReserva(dataReserva);
        return this;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataReserva);
    }

    

}
