package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.repository;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author laizorrane
 * Data Criacao: 12/08/2021 - 12:44
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmailEquals(String email);

}
