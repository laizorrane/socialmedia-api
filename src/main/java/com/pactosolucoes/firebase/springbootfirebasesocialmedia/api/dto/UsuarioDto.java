package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 09:54
 */

@ApiModel("Usuário")
public class UsuarioDto {

    public String nome;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String senha;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String imagemPerfil;

    public UsuarioDto(String nome, String senha, String email, String imagemPerfil) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.imagemPerfil = imagemPerfil;
    }

    public UsuarioDto(String nome, String imagemPerfil) {
        this.nome = nome;
        this.imagemPerfil = imagemPerfil;
    }

    public UsuarioDto() {
    }


}
