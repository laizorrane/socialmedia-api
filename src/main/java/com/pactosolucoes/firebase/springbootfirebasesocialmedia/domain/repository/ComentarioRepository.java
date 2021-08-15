package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.repository;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author laizorrane
 * Data Criacao: 15/08/2021 - 09:12
 */
public interface ComentarioRepository extends JpaRepository<Comentario, String> {


}
