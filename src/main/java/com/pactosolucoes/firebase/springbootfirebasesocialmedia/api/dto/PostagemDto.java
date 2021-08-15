package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 11:01
 */

@ApiModel("Postagem")
public class PostagemDto {

    @ApiModelProperty(required = true)
    public String conteudo;


    public PostagemDto(String conteudo) {
        this.conteudo = conteudo;
    }

    public PostagemDto() {
    }
}
