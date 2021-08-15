package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.impl;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.PostagemDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.PostagemResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Postagem;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Usuario;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.exceptions.ValidacaoException;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.repository.PostagemRepository;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.PostagemService;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.UsuarioService;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author laizorrane
 * Data Criacao: 13/08/2021 - 08:11
 */
@Service
public class PostagemServiceImpl implements PostagemService {

    @Autowired
    private PostagemRepository repository;
    @Autowired
    private UsuarioService usuarioService;


    @Override
    public String cadastrar(PostagemDto postagemDto, String emailCriador) {
        Usuario usuario = usuarioService.buscarPorEmail(emailCriador);

        Postagem postagem = new Postagem(postagemDto.conteudo);
        postagem.setCriador(usuario);
        postagem.setId(Utils.getIdAleatorio());

        return repository.save(postagem).getId();
    }

    @Override
    public void editar(String idPostagem, PostagemDto postagemDto) {
        Postagem postagem = getPostagemPorId(idPostagem);
        postagem.setConteudo(postagemDto.conteudo);

        repository.save(postagem);

    }

    private Postagem getPostagemPorId(String id) {
        Postagem postagem = repository.findById(id).orElse(null);
        if (postagem == null)
            throw new ValidacaoException(String.format("Postagem com o id = '%s' não foi encontrado.", id), 404);
        return postagem;
    }

    private boolean isUsuarioCriador(Postagem postagem, String emailUsuario) {
        return postagem.getCriador().getEmail().equalsIgnoreCase(emailUsuario);
    }

    @Override
    public void excluir(String emailUsuario, String idPostagem) {
        Postagem postagem = getPostagemPorId(idPostagem);
        if (!isUsuarioCriador(postagem, emailUsuario))
            throw new ValidacaoException("Você não tem permissão para excluir esta postagem.");

    }

    @Override
    public PostagemResponseDto buscarPostPeloId(String id) {
        Postagem postagem = getPostagemPorId(id);
        return getPostagemReponseDto(postagem);

    }

    @Override
    public List<PostagemResponseDto> buscarPostagens(String email) {
       List<Postagem> postagens = repository.findAll();
       return postagens.stream().map(this::getPostagemReponseDto).collect(Collectors.toList());
    }

    private PostagemResponseDto getPostagemReponseDto(Postagem postagem) {
        Usuario criador = postagem.getCriador();
        UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto( criador.getNome(), criador.getId().toString());
        return new PostagemResponseDto(postagem.getConteudo(), postagem.getData(), postagem.getId(), usuarioResponseDto);


    }
}
