package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.repository;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author laizorrane
 * Data Criacao: 12/08/2021 - 12:44
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmailEquals(String email);

    @Query("from Usuario u where upper(u.nome) like upper(:nome)")
    List<Usuario> buscarTodosContemNome(String nome);

    @Query(value = "select u.* " +
            " from usuario u " +
            " inner join seguindo s " +
            " on u.id = s.usuario_id " +
            " where s.quem_sigo_id = :id ", nativeQuery = true)
    List<Usuario> buscarTodosQueMeSegue(Long id);
}
