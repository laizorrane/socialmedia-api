package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.handler;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.DetalheErro;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.exceptions.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author laizorrane
 * Data Criacao: 12/08/2021 - 17:45
 */

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<DetalheErro> handleValidacaoException
            (ValidacaoException e, HttpServletRequest request) {
        DetalheErro erro = new DetalheErro(e.getMensagem(), e.getStatus());
        return ResponseEntity.status(e.getStatus()).body(erro);
    }

}
