package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.repository;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Postagem;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author laizorrane
 * Data Criacao: 12/08/2021 - 12:44
 */
public interface PostagemRepository extends JpaRepository<Postagem, String> {

}
