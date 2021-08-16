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
    @OneToMany
    @JoinTable(name = "LIKES", joinColumns = @JoinColumn(name = "POSTAGEM_ID"), inverseJoinColumns = @JoinColumn(name = "USUARIO_ID"))
    private List<Usuario> likes;

    public Postagem(String conteudo) {
        this.conteudo = conteudo;
        this.data = new Date();
    }

    public Postagem() {
    }

    public List<Usuario> getLikes() {
        if(this.likes == null){
            this.likes = new ArrayList<>();
        }
        return likes;
    }
}
