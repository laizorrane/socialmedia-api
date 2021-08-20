package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 14:32
 */
@ApiModel("Login Response")
public class JwtResponseDto implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final UsuarioResponseDto usuario;

    public JwtResponseDto(String jwttoken, UsuarioResponseDto usuario) {
        this.jwttoken = jwttoken;
        this.usuario = usuario;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public UsuarioResponseDto getUsuario() {
        return usuario;
    }
}
