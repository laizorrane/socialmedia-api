package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.impl;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.PostagemDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.PostagemResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.config.security.JwtTokenUtil;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Postagem;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Usuario;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.exceptions.ValidacaoException;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.repository.PostagemRepository;
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
 * Data Criacao: 13/08/2021 - 08:11
 */
@Service
public class PostagemServiceImpl implements PostagemService {

    @Autowired
    private PostagemRepository repository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ComentarioService comentarioService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

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

    @Override
    public Postagem getPostagemPorId(String id) {
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
        repository.delete(postagem);

    }

    @Override
    public PostagemResponseDto buscarPostPeloId(String id) {
        Postagem postagem = getPostagemPorId(id);
        return getPostagemReponseDto(postagem);

    }

    @Override
    public List<PostagemResponseDto> buscarPostagens() {
        List<Postagem> postagens = repository.findAll();
        return postagens.stream().map(this::getPostagemReponseDto).collect(Collectors.toList());
    }

    private PostagemResponseDto getPostagemReponseDto(Postagem postagem) {
        Long qtdeComentarios = comentarioService.qtdeComentarios(postagem.getId());
        return new PostagemResponseDto(
                postagem.getConteudo(),
                postagem.getData(),
                postagem.getId(),
                qtdeComentarios,
                Integer.toUnsignedLong(postagem.getLikes().size()),
                getUsuarioResponseDto(postagem.getCriador()),
                verificarSeTemLike(postagem.getLikes()));
    }

    private UsuarioResponseDto getUsuarioResponseDto(Usuario usuario){
        return new UsuarioResponseDto( usuario.getNome(), usuario.getImagemPerfil(), usuario.getId().toString());
    }

    @Override
    public void darLike(String id, String email) {
        Postagem postagem = getPostagemPorId(id);
        Usuario usuario = usuarioService.buscarPorEmail(email);
        if(verificarSeNaoTemLike(postagem.getLikes())){
            postagem.getLikes().add(usuario);
            repository.save(postagem);
        }
    }

    @Override
    public  void removerLike(String id, String email) {
        Postagem postagem = getPostagemPorId(id);
        Usuario usuario = usuarioService.buscarPorEmail(email);
        if(verificarSeTemLike(postagem.getLikes())) {
            postagem.getLikes().remove(usuario);
            repository.save((postagem));
        }
    }

    @Override
    public List<UsuarioResponseDto> buscarTodosLikesDeUmPost(String idPostagem) {
        Postagem postagem = getPostagemPorId(idPostagem);
        List<Usuario> likes = postagem.getLikes();
        return likes.stream().map(this::getUsuarioResponseDto).collect(Collectors.toList());
    }

    private boolean verificarSeNaoTemLike(List<Usuario> likes){
        return !verificarSeTemLike(likes);
    }

    private boolean verificarSeTemLike(List<Usuario> likes){
        if(likes == null) return false;
        Usuario like = likes.stream()
                .filter(usuario1 -> usuario1.getEmail().equalsIgnoreCase(jwtTokenUtil.getEmailUsuario()))
                .findFirst()
                .orElse(null);

        return like != null;
    }

    @Override
    public List<PostagemResponseDto> buscarPostagensDoUsuario(Long id) {
        List<Postagem> postagens = repository.findAllByCriador_IdEquals(id);
        return postagens.stream().map(this::getPostagemReponseDto).collect(Collectors.toList());
    }

    @Override
    public List<PostagemResponseDto> buscarPostagensDoUsuario(String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email);
        return buscarPostagensDoUsuario(usuario.getId());
    }

    @Override
    public List<PostagemResponseDto> buscarPostagensDeQuemSigo(String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email);
        List<Postagem> postagens = repository.buscarTodasDeQuemSigo(usuario.getId());
        return postagens.stream().map(this::getPostagemReponseDto).collect(Collectors.toList());
    }

}

