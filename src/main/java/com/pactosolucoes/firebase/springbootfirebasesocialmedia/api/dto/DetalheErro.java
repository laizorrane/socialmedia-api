package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto;

/**
 * @author laizorrane
 * Data Criacao: 12/08/2021 - 17:51
 */
public class DetalheErro {

    private String mensagem;
    private int status;
    private Long data;

    public DetalheErro(String mensagem, int status) {
        this.mensagem = mensagem;
        this.status = status;
        this.data = System.currentTimeMillis();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }
}
