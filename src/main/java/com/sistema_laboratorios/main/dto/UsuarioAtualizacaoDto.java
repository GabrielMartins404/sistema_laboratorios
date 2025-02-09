package com.sistema_laboratorios.main.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class UsuarioAtualizacaoDto {
    private Long id;

    private String curso;

    private String senha;

    private String nome;

    private LocalDate nascimento;


    public UsuarioAtualizacaoDto() {
    }

    public UsuarioAtualizacaoDto(Long id, String curso, String senha, String nome, LocalDate nascimento) {
        this.id = id;
        this.curso = curso;
        this.senha = senha;
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

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
