package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.controller.SeguindoController;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.PostagemDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.PostagemResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Postagem;

import java.util.List;

/**
 * @author laizorrane
 * Data Criacao: 13/08/2021 - 07:35
 */
public interface PostagemService {

    String cadastrar(PostagemDto postagemDto, String emailCriador);

    void editar(String idPostagem, PostagemDto postagemDto);

    void excluir(String emailUsuario , String idPostagem);

    PostagemResponseDto buscarPostPeloId(String id);

    List<PostagemResponseDto> buscarPostagens();

    List<PostagemResponseDto> buscarPostagensDoUsuario(Long id);

    List<PostagemResponseDto> buscarPostagensDoUsuario(String email);

    List<PostagemResponseDto> buscarPostagensDeQuemSigo(String email);

    void darLike(String id, String email);

    void removerLike(String id, String email);

    Postagem getPostagemPorId(String idPostagem);

    List<UsuarioResponseDto> buscarTodosLikesDeUmPost(String idPostagem);


}
