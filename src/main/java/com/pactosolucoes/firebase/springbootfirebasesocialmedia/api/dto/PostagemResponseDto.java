package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 13:28
 */

@ApiModel("Postagem Response")
public class PostagemResponseDto extends PostagemDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    public Date data;
    public String id;
    public Long qntdComentario;
    public Long qntdLike;
    public UsuarioResponseDto usuario;
    public boolean temLike;


    public PostagemResponseDto(String conteudo, Date data, String id, Long qntdComentario, Long qntdLike, UsuarioResponseDto usuario, boolean temLike) {
        super(conteudo);
        this.data = data;
        this.id = id;
        this.qntdComentario = qntdComentario;
        this.qntdLike = qntdLike;
        this.usuario = usuario;
        this.temLike = temLike;
    }



 }
