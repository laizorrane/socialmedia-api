package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.controller;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.PostagemResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 13:15
 */

@Api(tags = "Postagens")
@RequestMapping("/postagens")
@RestController
public class PostagemController {

    @ApiOperation("Listar todas as postagens")
    @GetMapping
    public ResponseEntity<List<PostagemResponseDto>> buscarTodasPostagens() {
        List<PostagemResponseDto> postagens = Arrays.asList(new PostagemResponseDto("Teste matriz", new Date(), "teste01", new UsuarioResponseDto("Laiz", "testeuser1")),
                new PostagemResponseDto("Teste secundário", new Date(), "teste02", new UsuarioResponseDto("Leticia", "testeuser2")));
        return ResponseEntity.ok(postagens);
    }

    @ApiOperation("Buscar postagem específica")
    @GetMapping("/{idPost}")
    public ResponseEntity<PostagemResponseDto> buscarPost(@ApiParam("Identificação da Postagem") @PathVariable("idPost") String id) {

        PostagemResponseDto post = new PostagemResponseDto("Teste três", new Date(), "teste03", new UsuarioResponseDto("Luana", "testeuser3"));
        post.id = id;
        return ResponseEntity.ok(post);
    }

}
