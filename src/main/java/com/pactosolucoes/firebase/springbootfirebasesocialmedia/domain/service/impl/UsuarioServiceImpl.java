package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.impl;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Usuario;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.exceptions.ValidacaoException;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.repository.UsuarioRepository;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.UsuarioService;
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
    public UsuarioResponseDto buscarPorEmail(String email) {
        Usuario usuario = repository.findByEmailEquals(email);
        return getUsuarioResponse(usuario);
    }

    @Override
    public void editar(Long idUsuario, UsuarioDto usuarioDto) {
        Usuario usuario = getUsuarioPorId(idUsuario);
        usuario.setNome(usuarioDto.nome);
        usuario.setSenha(usuarioDto.senha);
        usuario.setEmail(usuarioDto.email);
        repository.save(usuario);
    }

    @Override
    public Long cadastrar(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario(usuarioDto.nome, usuarioDto.email, usuarioDto.senha);

        usuario = repository.save(usuario);
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
        List<Usuario> listaUsuario = repository.buscarTodosContemNome("%"+nome+"%");

        if(listaUsuario == null)
            listaUsuario = new ArrayList<>();

        return listaUsuario.stream().map(this::getUsuarioResponseSemSenha).collect(Collectors.toList());
    }

    private UsuarioResponseDto getUsuarioResponse(Usuario usuario){
        return new UsuarioResponseDto(usuario.getNome(), usuario.getSenha(), usuario.getEmail(), usuario.getId().toString());
    }

    private UsuarioResponseDto getUsuarioResponseSemSenha(Usuario usuario){
        return new UsuarioResponseDto(usuario.getNome(), "****", usuario.getEmail(), usuario.getId().toString());
    }

    private Usuario getUsuarioPorId(Long id){
        Usuario usuario = repository.findById(id).orElse(null);
        if (usuario == null)
            throw new ValidacaoException(String.format("Usuário com o id = '%s' não foi encontrado.", id), 404);
        return usuario;
    }


    public void setRepository(UsuarioRepository repository) {
        this.repository = repository;
    }
}
