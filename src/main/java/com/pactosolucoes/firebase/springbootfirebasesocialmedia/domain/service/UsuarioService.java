package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Usuario;

import java.util.List;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 14:51
 */
public interface UsuarioService {

    Usuario buscarPorEmail(String email);

    void editar(Long idUsuario, UsuarioDto usuarioDto);

    Long cadastrar(UsuarioDto usuario);

    UsuarioResponseDto buscarPorId(Long id);

    List<UsuarioResponseDto> buscarTodosPorNome(String nome);

    void deletar(Long id);

    void seguir(Long idUsuario, String emailUsuarioLogado);


    void darUnfollow(Long idUsuario, String emailUsuario);

    List<UsuarioResponseDto> listarQuemEuSigo(String emailUsuario);

    List<UsuarioResponseDto> listarQuemMeSegue(String emailUsuario);
}




