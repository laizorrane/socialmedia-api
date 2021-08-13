package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.exceptions;

/**
 * @author laizorrane
 * Data Criacao: 12/08/2021 - 17:56
 */
public class ValidacaoException extends RuntimeException {

    private String mensagem;
    private int status;

    public ValidacaoException(String mensagem, int status) {
        super(mensagem);
        this.mensagem = mensagem;
        this.status = status;
    }

    public ValidacaoException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
        this.status = 400;
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
}
