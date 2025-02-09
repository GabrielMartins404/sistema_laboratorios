package com.sistema_laboratorios.main.dto;

import java.sql.Date;
import java.time.LocalDate;

public class UsuarioRetornoDto {
    private Long id;

    private String curso;

    private String matricula;

    private String nome;

    private LocalDate nascimento;


    public UsuarioRetornoDto() {
    }

    public UsuarioRetornoDto(Long id, String nome, String matricula, LocalDate nascimento, String curso) {
        this.id = id;
        this.curso = curso;
        this.matricula = matricula;
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurso() {
        return this.curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return this.nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

}
