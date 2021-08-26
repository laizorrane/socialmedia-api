package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.impl;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.ComentarioDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.ComentarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Comentario;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Postagem;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Usuario;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.exceptions.ValidacaoException;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.repository.ComentarioRepository;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.ComentarioService;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.PostagemService;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.UsuarioService;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author laizorrane
 * Data Criacao: 15/08/2021 - 09:13
 */

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository repository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PostagemService postagemService;

    @Override
    public String cadastrar(ComentarioDto comentarioDto, String emailCriador) {
        Usuario usuario = usuarioService.buscarPorEmail(emailCriador);
        Postagem postagem = postagemService.getPostagemPorId(comentarioDto.idPostagem);
        Comentario comentario = new Comentario(comentarioDto.conteudo);
        comentario.setCriador(usuario);
        comentario.setId(Utils.getIdAleatorio());
        comentario.setPostagem(postagem);
        return repository.save(comentario).getId();
    }

    @Override
    public List<ComentarioResponseDto> buscarTodosComentariosDeUmPost(String idPostagem) {
        List<Comentario> comentarios = repository.findAllByPostagem_id(idPostagem);
        return comentarios.stream().map(this::getComentarioResponseDto).collect(Collectors.toList());
    }
    private ComentarioResponseDto getComentarioResponseDto(Comentario comentario) {
        Usuario criador = comentario.getCriador();
        UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto( criador.getNome(), criador.getImagemPerfil(), criador.getId().toString());
        return new ComentarioResponseDto(comentario.getConteudo(), comentario.getData(), comentario.getId(), comentario.getPostagem().getId(), usuarioResponseDto);

    }

    @Override
    public ComentarioResponseDto buscarComentarioPeloId(String id) {
        Comentario comentario = getComentarioPorId(id);
        return getComentarioResponseDto(comentario);
    }

    private Comentario getComentarioPorId(String id) {
        Comentario comentario= repository.findById(id).orElse(null);
        if (comentario == null)
            throw new ValidacaoException(String.format("Comentario com o id = '%s' não foi encontrado.", id), 404);
        return comentario;
    }

    @Override
    public void excluir(String idComentario, String emailUsuario) {
        Comentario comentario = getComentarioPorId(idComentario);
        if (!isUsuarioCriador(comentario, emailUsuario))
            throw new ValidacaoException("Você não tem permissão para excluir este comentário.");
        repository.delete(comentario);

    }
    private boolean isUsuarioCriador(Comentario comentario, String emailUsuario) {
        return comentario.getCriador().getEmail().equalsIgnoreCase(emailUsuario);
    }

    @Override
    public void editar(String idComentario, ComentarioDto comentarioDto) {
        Comentario comentario = getComentarioPorId(idComentario);
        comentario.setConteudo(comentarioDto.conteudo);

        repository.save(comentario);

    }

    @Override
    public Long qtdeComentarios(String idPostagem) {
        return repository.countComentarioByPostagem_Id(idPostagem);
    }
}
