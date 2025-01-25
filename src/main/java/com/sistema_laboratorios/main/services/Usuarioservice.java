package com.sistema_laboratorios.main.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema_laboratorios.main.models.Usuario;
import com.sistema_laboratorios.main.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class Usuarioservice{
   
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarUsuarioPorId(Long idUsuario){

        Optional<Usuario> usuario = this.usuarioRepository.findById(idUsuario);
        return usuario.orElseThrow(() -> new RuntimeException("Falha ao buscar usuário por id!"));

    }
    
   public Usuario buscarUsuarioPorMatricula(String matricula){
        
    Optional<Usuario> usuario = this.usuarioRepository.userFindMatricula(matricula);
    return usuario.orElseThrow(() -> new RuntimeException("Falha ao buscar usuário por matrícula!"));
   }
   
    @Transactional
    public Usuario criarUsuario(Usuario usuario){
         

    }

}