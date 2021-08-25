package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.repository;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author laizorrane
 * Data Criacao: 12/08/2021 - 12:44
 */
public interface PostagemRepository extends JpaRepository<Postagem, String> {

    List<Postagem> findAllByCriador_IdEquals(Long id);

    @Query(value = " select p.* " +
            " from postagem p " +
            " inner join seguindo s " +
            " on p.usuarioid = s.quem_sigo_id " +
            " where s.usuario_id = :id " +
            " order by p.data desc ", nativeQuery = true)
    List<Postagem> buscarTodasDeQuemSigo(Long id);
}
