package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto;

import io.swagger.annotations.ApiModel;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 13:43
 */

@ApiModel("Usuário")
public class UsuarioResponseDto extends UsuarioDto {

    public String id;

    public UsuarioResponseDto(String nome, String imagemPerfil, String id) {
        super(nome, imagemPerfil);
        this.id = id;
    }

    public UsuarioResponseDto(String nome, String senha, String email, String id, String imagemPerfil) {
        super(nome, senha, email, imagemPerfil);
        this.id = id;
    }
}


