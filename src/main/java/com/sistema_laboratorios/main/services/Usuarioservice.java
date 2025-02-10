package com.sistema_laboratorios.main.services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.sistema_laboratorios.main.dto.UsuarioRetornoDto;
import com.sistema_laboratorios.main.models.Usuario;
import com.sistema_laboratorios.main.repositories.UsuarioRepository;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;

@Service
public class Usuarioservice{
   
    private final UsuarioRepository usuarioRepository;

    public Usuarioservice(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /* Métodos do services */

    public Usuario buscarUsuarioPorId(Long idUsuario){
        Optional<Usuario> usuario = this.usuarioRepository.findById(idUsuario);
        return usuario.orElseThrow(() -> new RuntimeException("Falha ao buscar usuário por id!"));
    }
    
    //Essa função tem como propósito, converter um usuário em usuárioDTO
    public UsuarioRetornoDto criarUsuarioDto(Usuario usuario){
        UsuarioRetornoDto usuarioDto = new UsuarioRetornoDto(
            usuario.getId(), 
            usuario.getNome(), 
            usuario.getMatricula(), 
            usuario.getNascimento(),
            usuario.getCurso()
        );
        return usuarioDto;
    }

    @Transactional
    public Usuario criarUsuario(Usuario usuario){
        
         if(this.usuarioRepository.userFindMatricula(usuario.getMatricula()).isPresent()){
            throw new RuntimeException("Usuário já existente! Fazer login, por favor!");
         }else{
            usuario.setId(null);
            this.usuarioRepository.save(usuario);
            return usuario;
         }
    }

    @Transactional
    public UsuarioRetornoDto atualizarUsuario(Usuario usuarioDto){
        Optional<Usuario> newUsuario = this.usuarioRepository.findById(usuarioDto.getId());

        if(newUsuario.isPresent()){
    
            newUsuario.get().setNome(usuarioDto.getNome());
            newUsuario.get().setNascimento(usuarioDto.getNascimento());
            //System.out.println("Resultado ==========" + usuarioDto.toString());
            newUsuario.get().setCurso(usuarioDto.getCurso());
            if(StringUtils.isNotBlank(usuarioDto.getSenha())){
                newUsuario.get().setSenha(usuarioDto.getSenha());
            }

            this.usuarioRepository.save(newUsuario.get());
            return this.criarUsuarioDto(newUsuario.get());


        } else{
            throw new RuntimeException("Falha ao atualizar usuário! Erro de id");
        }
    }
    
    public UsuarioRetornoDto loginUsuario (String matricula, String senha){
        Optional<Usuario> usuario = this.usuarioRepository.userFindLogin(matricula, senha);

        if (usuario.isPresent()) {
            return this.criarUsuarioDto(usuario.get());
        } else {
            throw new RuntimeException("Usuario e/ou senha incorretos");
        }
        //return usuario.orElseThrow(() -> new RuntimeException("Usuario e/ou senha incorretos"));
    }

}
    

