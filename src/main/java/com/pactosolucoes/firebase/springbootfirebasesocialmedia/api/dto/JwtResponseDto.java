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

    public JwtResponseDto(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }


}
