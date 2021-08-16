package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.controller.SeguindoController;
import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 13:28
 */

@ApiModel("Postagem Response")
public class PostagemResponseDto extends PostagemDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
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
