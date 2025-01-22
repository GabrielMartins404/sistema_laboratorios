package com.sistema_laboratorios.main.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema_laboratorios.main.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    //Repository para retornar um unico usuário de acordo com o login e senha
    @Query(value = "SELECT * FROM usuario WHERE matricula = :matricula AND senha = :senha", nativeQuery = true)
    Optional<Usuario> userFindLogin(@Param("matricula") String matricula, @Param("senha") String senha);

}
