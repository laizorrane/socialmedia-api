package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import java.util.Date;


/**
 * @author laizorrane
 * Data Criacao: 15/08/2021 - 09:40
 */

@ApiModel("Comentario Response")
public class ComentarioResponseDto extends ComentarioDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    public Date data;
    public String idComentario;
    public UsuarioResponseDto usuario;

    public ComentarioResponseDto(String conteudo, Date data, String id, String idPostagem, UsuarioResponseDto usuario) {
        super(conteudo, idPostagem);
        this.data = data;
        this.idComentario = id;
        this.usuario = usuario;
    }


}
