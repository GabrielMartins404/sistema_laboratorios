package com.sistema_laboratorios.main.services;

import java.util.Optional;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema_laboratorios.main.models.Usuario;
import com.sistema_laboratorios.main.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import java.util.Objects;

@Service
public class Usuarioservice{
   
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarUsuarioPorId(Long idUsuario){

        Optional<Usuario> usuario = this.usuarioRepository.findById(idUsuario);
        return usuario.orElseThrow(() -> new RuntimeException("Falha ao buscar usuário por id!"));

    }
    
    @Transactional
    public Usuario criarUsuario(Usuario usuario){
        
         if(this.usuarioRepository.userFindMatricula(usuario.getMatricula()).isPresent()){
            throw new RuntimeException("Usuário já existente!");
         }else{
            usuario.setId(null);
            this.usuarioRepository.save(usuario);
            return usuario;
         }
    }

    @Transactional
    public Usuario atualizarUsuario(Usuario usuario){
        Optional<Usuario> newUsuario = this.usuarioRepository.findById(usuario.getId());

        if(newUsuario.isPresent()){
            newUsuario.get().setNome(usuario.getNome());
            newUsuario.get().setNascimento(usuario.getNascimento());
            newUsuario.get().setSenha(usuario.getSenha());
            return this.usuarioRepository.save(newUsuario.get());
        } else{
            throw new RuntimeException("Falha ao atualizar usuário! Erro de id");
        }
    }
    public Usuario loginUsuario (String matricula, String senha){
        Optional<Usuario> usuario = this.usuarioRepository.userFindLogin(matricula, senha);
       return usuario.orElseThrow(() -> new RuntimeException("Usuario e/ou senha incorretos"));
        }

    public Usuarioservice() {
    }

    public Usuarioservice(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioRepository getUsuarioRepository() {
        return this.usuarioRepository;
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuarioservice usuarioRepository(UsuarioRepository usuarioRepository) {
        setUsuarioRepository(usuarioRepository);
        return this;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }
}
    

