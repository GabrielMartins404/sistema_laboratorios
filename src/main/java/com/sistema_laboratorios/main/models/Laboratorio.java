package com.sistema_laboratorios.main.models;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = Laboratorio.TABLE_NAME)
public class Laboratorio {
    
    public static final String TABLE_NAME = "laboratorio";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id; 

    @Column(name = "descricao", length = 100, nullable = false )
    @NotBlank
    private String detalhamentoLab; 

    @Column(name = "detalhesLab", columnDefinition = "TEXT", nullable = true)
    @NotBlank
    private String descricao;
    
    @Column(name = "capacidade", nullable = true)
    @NotNull
    private Integer capacidade; 

    /* Anotações das chaves estrangeiras */
    @OneToMany(mappedBy = "laboratorioHorario")
    @JsonBackReference
    private List<Horario> horarios = new ArrayList<Horario>();

    public Laboratorio() {
    }

    public Laboratorio(long id, String detalhamentoLab, String descricao, Integer capacidade) {
        this.id = id;
        this.detalhamentoLab = detalhamentoLab;
        this.descricao = descricao;
        this.capacidade = capacidade;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDetalhamentoLab() {
        return this.detalhamentoLab;
    }

    public void setDetalhamentoLab(String detalhamentoLab) {
        this.detalhamentoLab = detalhamentoLab;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCapacidade() {
        return this.capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Laboratorio id(long id) {
        setId(id);
        return this;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, detalhamentoLab, descricao, capacidade);
    }

  
}
