package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.controller;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.config.security.JwtTokenUtil;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.helpers.AttributeListImpl;

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

    //listar quem eu sigo
    @ApiOperation("Listar todos que eu sigo.")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listar(){
        List<UsuarioResponseDto> listaQuemEuSigo = service.listarQuemEuSigo(tokenUtil.getEmailUsuario());
        return ResponseEntity.ok(listaQuemEuSigo);
    }

    @ApiOperation("Listar todos que me seguem.")
    @GetMapping("/seguidores")
    public ResponseEntity<List<UsuarioResponseDto>> listarSeguidores(){
        List<UsuarioResponseDto> listaQuemMeSegue = service.listarQuemMeSegue(tokenUtil.getEmailUsuario());
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
