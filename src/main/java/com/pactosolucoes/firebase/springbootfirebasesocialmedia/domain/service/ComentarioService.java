package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.ComentarioDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.ComentarioResponseDto;

import java.util.List;

/**
 * @author laizorrane
 * Data Criacao: 15/08/2021 - 09:39
 */
public interface ComentarioService {

    String cadastrar(ComentarioDto comentarioDto, String emailCriador);

    List<ComentarioResponseDto> buscarTodosComentariosDeUmPost(String idPostagem);

    void excluir(String idComentario, String emailUsuario);

   void editar(String idComentario, ComentarioDto comentarioDto);

    ComentarioResponseDto buscarComentarioPeloId(String id);

    Long qtdeComentarios(String idPostagem);

}

