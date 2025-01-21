package com.sistema_laboratorios.main.models;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = Reserva.TABLE_NAME)
public class Reserva{
    
    public static final String TABLE_NAME = "reserva"; 

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dataReserva")
    @NotNull
    private Date dataReserva;
    

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
