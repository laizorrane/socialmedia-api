package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto;

import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 13:28
 */

@ApiModel("Postagem Response")
public class PostagemResponseDto extends PostagemDto {

    public Date data;
    public String id;
    public UsuarioResponseDto usuario;

    public PostagemResponseDto(String conteudo, Date data, String id, UsuarioResponseDto usuario) {
        super(conteudo);
        this.data = data;
        this.id = id;
        this.usuario = usuario;
    }
}
