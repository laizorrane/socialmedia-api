package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 14:51
 */
public interface UsuarioService {

    UsuarioResponseDto buscarPorEmail(String email);
    Long cadastrar(UsuarioDto usuario);
    UsuarioResponseDto buscarPorId(Long id);

}




