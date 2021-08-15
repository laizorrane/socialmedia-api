package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author laizorrane
 * Data Criacao: 13/08/2021 - 08:56
 */

@Entity
public @Data class Postagem {

    @Id
    private String id;
    private String conteudo;
    private Date data;

    @JoinColumn(name = "usuarioId")
    @ManyToOne
    private Usuario criador;

    public Postagem(String conteudo) {
        this.conteudo = conteudo;
        this.data = new Date();
    }

    public Postagem() {
    }
}
