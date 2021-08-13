package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 09:54
 */

public class UsuarioDto {

    public String nome;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String senha;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String email;

    public UsuarioDto(String nome, String senha, String email) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    public UsuarioDto(String nome) {
        this.nome = nome;
    }

    public UsuarioDto() {
    }


}
