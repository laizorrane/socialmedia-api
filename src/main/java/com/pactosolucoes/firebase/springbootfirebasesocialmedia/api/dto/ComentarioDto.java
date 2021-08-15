package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 10:58
 */
public class ComentarioDto {

    public String conteudo;
    public String idPostagem;


    public ComentarioDto(String conteudo, String idPostagem) {
        this.conteudo = conteudo;
        this.idPostagem = idPostagem;

    }

    public ComentarioDto() {
    }


}
