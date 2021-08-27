package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 14:41
 */
@Entity
public @Data class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String imagemPerfil;

    @ManyToMany
    @JoinTable(name = "SEGUINDO",
            joinColumns = @JoinColumn(name = "USUARIO_ID"),
            inverseJoinColumns = @JoinColumn(name = "QUEM_SIGO_ID"))
    private List<Usuario> listaQuemSigo;

    public Usuario(String nome, String email, String senha, String imagemPerfil) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.imagemPerfil = imagemPerfil;
    }

    public Usuario() {
    }

    public List<Usuario> getListaQuemSigo() {
        if(this.listaQuemSigo == null){
            this.listaQuemSigo = new ArrayList<>();
        }
        return listaQuemSigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return email.equals(usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
