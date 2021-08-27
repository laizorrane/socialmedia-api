package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.impl;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Usuario;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.exceptions.ValidacaoException;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.repository.UsuarioRepository;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 14:41
 */

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario buscarPorEmail(String email) {
        Usuario usuario = repository.findByEmailEquals(email);
        if (usuario == null)
            throw new ValidacaoException(String.format("Não foi encontrado usuário com o email: %s", email));
        return usuario;
    }

    @Override
    public void editar(Long idUsuario, UsuarioDto usuarioDto) {
        Usuario usuario = getUsuarioPorId(idUsuario);
        usuario.setNome(usuarioDto.nome);
        usuario.setSenha(usuarioDto.senha);
        usuario.setEmail(usuarioDto.email);
        usuario.setImagemPerfil(usuarioDto.imagemPerfil);
        repository.save(usuario);
    }

    @Override
    public Long cadastrar(UsuarioDto usuarioDto) {
        this.validar(usuarioDto);
        Usuario usuario = new Usuario(usuarioDto.nome, usuarioDto.email, usuarioDto.senha, usuarioDto.imagemPerfil);
        usuario = repository.save(usuario);
        seguir(usuario.getId(), usuario.getEmail());
        return usuario.getId();
    }


    @Override
    public UsuarioResponseDto buscarPorId(Long id) {
        Usuario usuario = getUsuarioPorId(id);
        return getUsuarioResponse(usuario);

    }

    @Override
    public void deletar(Long id) {
        Usuario usuario = getUsuarioPorId(id);
        repository.delete(usuario);
    }

    @Override
    public List<UsuarioResponseDto> buscarTodosPorNome(String nome) {
        List<Usuario> listaUsuario = repository.buscarTodosContemNome("%" + nome + "%");

        if (listaUsuario == null)
            listaUsuario = new ArrayList<>();

        return listaUsuario.stream().map(this::getUsuarioResponseSemSenha).collect(Collectors.toList());
    }

    private UsuarioResponseDto getUsuarioResponse(Usuario usuario) {
        return new UsuarioResponseDto(usuario.getNome(), usuario.getSenha(), usuario.getEmail(), usuario.getId().toString(), usuario.getImagemPerfil());
    }

    private UsuarioResponseDto getUsuarioResponseSemSenha(Usuario usuario) {
        return new UsuarioResponseDto(usuario.getNome(), "****", usuario.getEmail(), usuario.getId().toString(), usuario.getImagemPerfil());
    }

    private Usuario getUsuarioPorId(Long id) {
        Usuario usuario = repository.findById(id).orElse(null);
        if (usuario == null)
            throw new ValidacaoException(String.format("Usuário com o id = '%s' não foi encontrado.", id), 404);
        return usuario;
    }


    public void setRepository(UsuarioRepository repository) {
        this.repository = repository;
    }

    private void validar(UsuarioDto usuarioDto) {

        if (usuarioDto == null)
            throw new ValidacaoException("Obrigatório informar usuário");

        if (StringUtils.isBlank(usuarioDto.nome))
            throw new ValidacaoException("Obrigatório informar um nome");

        if (StringUtils.isBlank(usuarioDto.email))
            throw new ValidacaoException("Obrigatório informar email");

        if (StringUtils.isBlank(usuarioDto.senha))
            throw new ValidacaoException("Obrigatório informar senha");

        if (!usuarioDto.email.contains("@"))
            throw new ValidacaoException("Email informado é inválido");

        if (repository.findByEmailEquals(usuarioDto.email) != null)
            throw new ValidacaoException(String.format("E-mail informado já existe: %s", usuarioDto.email));
    }

    @Override
    public void seguir(Long idUsuario, String emailUsuarioLogado) {
        Usuario quemVouSeguir = getUsuarioPorId(idUsuario);
        Usuario usuarioLogado = buscarPorEmail(emailUsuarioLogado);
        if (naoSigo(usuarioLogado.getListaQuemSigo(), quemVouSeguir.getId())) {
            usuarioLogado.getListaQuemSigo().add(quemVouSeguir);
            repository.save(usuarioLogado);
        }
    }

    private boolean naoSigo(List<Usuario> listaQueSigo, Long id) {
        return !jaSigo(listaQueSigo, id);
    }

    private boolean jaSigo(List<Usuario> listaQueSigo, Long id) {
        return listaQueSigo.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst()
                .orElse(null) != null;
    }

    @Override
    public void darUnfollow(Long idUsuario, String emailUsuario) {
        Usuario quemVouPararDeSeguir = getUsuarioPorId(idUsuario);
        Usuario usuarioLogado = buscarPorEmail(emailUsuario);
        if (jaSigo(usuarioLogado.getListaQuemSigo(), quemVouPararDeSeguir.getId()) && !quemVouPararDeSeguir.equals(usuarioLogado)) {
            usuarioLogado.setListaQuemSigo(removerQuemSigo(usuarioLogado.getListaQuemSigo(), quemVouPararDeSeguir.getId()));
            repository.save(usuarioLogado);
        }
    }

    private List<Usuario> removerQuemSigo(List<Usuario> listaQuemSigo, Long idRemocao) {
        return listaQuemSigo.stream()
                .filter(usuario -> !usuario.getId().equals(idRemocao))
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioResponseDto> listarQuemEuSigo(String emailUsuario) {
        Usuario usuario = buscarPorEmail(emailUsuario);
        return usuario.getListaQuemSigo()
                .stream().map(this::getUsuarioResponse)
                .filter(usuarioResponseDto -> !usuarioResponseDto.email.equalsIgnoreCase(emailUsuario))
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioResponseDto> listarQuemMeSegue(String emailUsuario) {
        Usuario usuario = buscarPorEmail(emailUsuario);
        List<Usuario> usuarios = repository.buscarTodosQueMeSegue(usuario.getId());
        if (usuarios != null && !usuarios.isEmpty()) {
            return usuarios.stream().map(this::getUsuarioResponse).filter(usuarioResponseDto -> !usuarioResponseDto.email.equalsIgnoreCase(emailUsuario))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
