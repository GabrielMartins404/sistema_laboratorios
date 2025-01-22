package com.sistema_laboratorios.main.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonManagedReference;

// Tabela de usuarios
@Entity //Defino a classe como entidade
@Table(name = Usuario.TABLE_NAME) //Indicação do nome da tabela no banco de dados
public class Usuario {
    //Informo aqui o nome da minha tabela. O static informa que não preciso instanciar a classe
    public static final String TABLE_NAME = "usuario";

    @Id //Anotação referente ao id da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indicação de ID automático
    @Column(name = "id", unique = true) //Indico o nome da tabela e que a mesma não pode se repetir
    private Long id;
    
    @Column(name = "curso", length = 50, nullable = false)  //Determino o nome e indico a quantidade máxima varchar(50)
    @NotBlank //Indico que não pode nem ser vazio e nulo
    private String curso;

    @Column(name = "matricula", length = 20, nullable = false, unique = true)
    @NotBlank
    private String matricula;

    @Column(name = "senha", length = 20, nullable = false)
    @Size(min = 8, max = 20) //Indicação do tamanho máximo e minimo do campo
    @NotBlank
    private String senha;

    @Column(name = "nome", length = 100, nullable = false)
    @NotBlank
    private String nome;

    @Column(name = "nascimento")
    private Date nascimento;

    /* Anotações das chaves estrangeiras */
    @OneToMany(mappedBy = "usuarioReserva") //Indico que o um usuario
    @JsonManagedReference
    private List<Reserva> reservas = new ArrayList<Reserva>();

    public Usuario() {}

    public Usuario(Long id, String curso, String senha, Date nascimento, String nome, String matricula) {
        this.id = id;
        this.curso = curso;
        this.senha = senha;
        this.nascimento = nascimento;
        this.nome = nome;
        this.matricula = matricula;
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

    public Date getNascimento() {
        return this.nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Usuario id(Long id) {
        setId(id);
        return this;
    }

    public Usuario curso(String curso) {
        setCurso(curso);
        return this;
    }

    public Usuario senha(String senha) {
        setSenha(senha);
        return this;
    }

    public Usuario nascimento(Date nascimento) {
        setNascimento(nascimento);
        return this;
    }

    public Usuario nome(String nome) {
        setNome(nome);
        return this;
    }

    public Usuario matricula(String matricula) {
        setMatricula(matricula);
        return this;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, curso, senha, nascimento, nome, matricula);
    }
}
