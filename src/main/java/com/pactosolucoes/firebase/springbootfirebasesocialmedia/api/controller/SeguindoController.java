package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.controller;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.config.security.JwtTokenUtil;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author laizorrane
 * Data Criacao: 13/08/2021 - 13:16
 */

@Api(tags = "Seguindo")
@RequestMapping("/seguindo")
@RestController
public class SeguindoController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private JwtTokenUtil tokenUtil;

    @ApiOperation("Listar todos que usuário segue.")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listar(@ApiParam(required = true, value = "Identificação do usuário requerido.") @RequestParam(value = "email", required = false) String emailFront){
        String email = StringUtils.isBlank(emailFront) ? tokenUtil.getEmailUsuario(): emailFront;
        List<UsuarioResponseDto> listaQuemEuSigo = service.listarQuemEuSigo(email);
        return ResponseEntity.ok(listaQuemEuSigo);
    }

    @ApiOperation("Listar todos que seguem usuário.")
    @GetMapping("/seguidores")
    public ResponseEntity<List<UsuarioResponseDto>> listarSeguidores(@ApiParam(required = true, value = "Identificação do usuário requerido.") @RequestParam(value = "email", required = false) String emailFront){
        String email = StringUtils.isBlank(emailFront) ? tokenUtil.getEmailUsuario(): emailFront;
        List<UsuarioResponseDto> listaQuemMeSegue = service.listarQuemMeSegue(email);
        return ResponseEntity.ok(listaQuemMeSegue);
    }

    @ApiOperation("Seguir um usuário.")
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> seguir(@ApiParam(required = true, value = "Identificação de um usuário.") @PathVariable("id") Long idUsuario){
        service.seguir(idUsuario, tokenUtil.getEmailUsuario());
        return ResponseEntity.accepted().build();
    }

    @ApiOperation("Parar de seguir um usuário.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> darUnfollow(@ApiParam(required = true, value = "Identificação de um usuário.") @PathVariable("id") Long idUsuario){
        service.darUnfollow(idUsuario, tokenUtil.getEmailUsuario());
        return ResponseEntity.accepted().build();
    }

}
