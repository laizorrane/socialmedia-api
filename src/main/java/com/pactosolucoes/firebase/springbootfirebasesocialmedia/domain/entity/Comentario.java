package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @author laizorrane
 * Data Criacao: 13/08/2021 - 13:12
 */
@Entity
public @Data class Comentario {

    @Id
    private String id;
    private String conteudo;
    private Date data;

    @JoinColumn(name = "postagem_id")
    @ManyToOne
    private Postagem postagem;

    @JoinColumn(name = "usuario_id")
    @ManyToOne
    private Usuario criador;

    public Comentario(String conteudo) {
        this.conteudo = conteudo;
        this.data = new Date();
    }

    public Comentario() {
    }

}
