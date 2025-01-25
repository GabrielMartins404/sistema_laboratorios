package com.sistema_laboratorios.main.controllers;
import java.net.URI;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.sistema_laboratorios.main.services.Usuarioservice;
import com.sistema_laboratorios.main.models.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@Validated
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private Usuarioservice usuarioservice;
    
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario>buscarUsuarioPorId(@PathVariable Long idUsuario){
        Usuario usuario = this.usuarioservice.buscarUsuarioPorId(idUsuario);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
        this.usuarioservice.criarUsuario(usuario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idUsuario}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarUsuario(@RequestBody Usuario usuario, @PathVariable Long idUsuario){
        usuario.setId(idUsuario);
        this.usuarioservice.atualizarUsuario(usuario);
        return ResponseEntity.noContent().build();
    }
    public UsuarioController() {
    
    }
    
    public UsuarioController(Usuarioservice usuarioservice) {
        this.usuarioservice = usuarioservice;
    }

    public Usuarioservice getUsuarioservice() {
        return this.usuarioservice;
    }

    public void setUsuarioservice(Usuarioservice usuarioservice) {
        this.usuarioservice = usuarioservice;
    }

    public UsuarioController usuarioservice(Usuarioservice usuarioservice) {
        setUsuarioservice(usuarioservice);
        return this;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

}
