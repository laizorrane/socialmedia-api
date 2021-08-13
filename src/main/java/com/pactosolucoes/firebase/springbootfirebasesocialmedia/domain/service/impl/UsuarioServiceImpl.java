package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.impl;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Usuario;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.exceptions.ValidacaoException;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.repository.UsuarioRepository;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return new UsuarioResponseDto(usuario.getNome(),
                usuario.getSenha(), usuario.getEmail(), usuario.getId().toString());
    }

    @Override
    public Long cadastrar(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario(usuarioDto.nome, usuarioDto.email, usuarioDto.senha);

        usuario = repository.save(usuario);
        return usuario.getId();
    }

    @Override
    public UsuarioResponseDto buscarPorId(Long id) {
        Usuario usuario = repository.findById(id).orElse(null);
        if (usuario == null)
            throw new ValidacaoException(String.format("Usuário '%s' não foi encontrado.", id), 404);
        return new UsuarioResponseDto(usuario.getNome(), usuario.getSenha(), usuario.getEmail(), usuario.getId().toString());

    }

    public void setRepository(UsuarioRepository repository) {
        this.repository = repository;
    }
}
