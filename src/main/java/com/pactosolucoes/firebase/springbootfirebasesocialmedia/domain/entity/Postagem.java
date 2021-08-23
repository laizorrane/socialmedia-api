package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.PostagemResponseDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @ManyToMany
    @JoinTable(
            name = "LIKES",
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = { "POSTAGEM_ID", "USUARIO_ID" })},
            joinColumns = @JoinColumn(name = "POSTAGEM_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID")
    )
    private List<Usuario> likes;

    public Postagem(String conteudo) {
        this.conteudo = conteudo;
        this.data = new Date();
    }

    public Postagem() {
    }

}
