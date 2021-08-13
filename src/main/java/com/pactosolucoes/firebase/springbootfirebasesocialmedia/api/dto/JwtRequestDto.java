package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 14:32
 */
@ApiModel("Login")
public class JwtRequestDto implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;

    @ApiModelProperty(required = true)
    private String email;

    @ApiModelProperty(required = true)
    private String senha;

    public JwtRequestDto(){
    }


    public JwtRequestDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
