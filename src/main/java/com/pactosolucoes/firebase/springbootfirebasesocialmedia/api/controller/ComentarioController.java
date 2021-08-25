package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.controller;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.ComentarioDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.ComentarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.config.security.JwtTokenUtil;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.ComentarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author laizorrane
 * Data Criacao: 13/08/2021 - 13:16
 */

@Api(tags = "Comentários")
@RequestMapping("/comentarios")
@RestController
public class ComentarioController {

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Autowired
    private ComentarioService service;


    @ApiOperation("Cadastrar um novo comentário para a postagem.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> cadastrarComentario(@ApiParam(name = "Comentário", required = true, value = "Representação de um comentário.") @RequestBody ComentarioDto comentarioDto){
       String idPostagem = service.cadastrar(comentarioDto, tokenUtil.getEmailUsuario());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(idPostagem).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation("Listar comentários de uma postagem.")
    @GetMapping
    public ResponseEntity<List<ComentarioResponseDto>> buscarTodosComentarioDeUmaPostagem(@ApiParam(required = true, value = "Identificação de postagem") @RequestParam("idPostagem") String idPostagem){
        List<ComentarioResponseDto> comentarios = service.buscarTodosComentariosDeUmPost(idPostagem);
        return ResponseEntity.ok(comentarios);
    }

    @ApiOperation("Deletar um comentário (apenas o criador pode excluir).")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> removerComentario(@ApiParam(required = true, value = "Identificação de um comentário") @PathVariable("id") String idComentario){
        service.excluir(idComentario, tokenUtil.getEmailUsuario());
        return ResponseEntity.accepted().build();
    }
    @ApiOperation("Buscar comentário específico")
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioResponseDto> buscarComentario(@ApiParam("Identificação do comentário") @PathVariable("id") String id) {
        return ResponseEntity.ok(service.buscarComentarioPeloId(id));
    }

    @ApiOperation("Editar um comentário.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> editarComentario(@ApiParam(name = "Comentario", required = true, value = "Representação de um comentário.") @PathVariable("id") String id, @RequestBody ComentarioDto comentarioDto) {

        service.editar(id, comentarioDto);
        return ResponseEntity.accepted().build();
    }

}
