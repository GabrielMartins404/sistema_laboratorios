package com.sistema_laboratorios.main.controllers;
import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.sistema_laboratorios.main.services.Usuarioservice;
import com.sistema_laboratorios.main.dto.UsuarioRetornoDto;
import com.sistema_laboratorios.main.models.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@Validated
@RequestMapping("/usuario")
public class UsuarioController {
    
    private final Usuarioservice usuarioservice;

    public UsuarioController(Usuarioservice usuarioservice) {
        this.usuarioservice = usuarioservice;
    }
    
    //Essa rota serve para o redirecionar a navegação após a criação de um usuário
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario>buscarUsuarioPorId(@PathVariable Long idUsuario){
        Usuario usuario = this.usuarioservice.buscarUsuarioPorId(idUsuario);
        return ResponseEntity.ok().body(usuario);
    }

    //Implementação do controller de login de usuário
    //Como não posso retornar a senha, uso o ResponseEntity<?> para que eu consiga personalizar o retorno
    @GetMapping("/loginUsuario")
    public ResponseEntity<?> loginUsuario(@RequestParam String matricula, @RequestParam String senha){
        UsuarioRetornoDto usuarioDto = this.usuarioservice.loginUsuario(matricula, senha);
        return ResponseEntity.ok().body(usuarioDto);
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
        this.usuarioservice.criarUsuario(usuario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idUsuario}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioRetornoDto> atualizarUsuario(@RequestBody Usuario usuario, @PathVariable Long idUsuario){
        usuario.setId(idUsuario);
        UsuarioRetornoDto usuarioRetorno = this.usuarioservice.atualizarUsuario(usuario);
        return ResponseEntity.ok(usuarioRetorno);
    }
    
}
