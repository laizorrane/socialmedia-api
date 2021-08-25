package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.controller;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.PostagemDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.PostagemResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.config.security.JwtTokenUtil;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.PostagemService;
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
 * Data Criacao: 11/08/2021 - 13:15
 */

@Api(tags = "Postagens")
@RequestMapping("/postagens")
@RestController
public class PostagemController {

    @Autowired
    private PostagemService service;
    @Autowired
    private JwtTokenUtil tokenUtil;


    @ApiOperation("Listar todas as postagens")
    @GetMapping
    public ResponseEntity<List<PostagemResponseDto>> buscarTodasPostagens() {
        return ResponseEntity.ok(service.buscarPostagens());
    }

    @ApiOperation("Listar todas as postagens de um usuário")
    @GetMapping("/postagemUsuario")
    public ResponseEntity<List<PostagemResponseDto>> buscarTodasPostagensDeUmUsuario(@ApiParam("Lista de postagens do usuário") @RequestParam("idUser") Long id) {
        return ResponseEntity.ok(service.buscarPostagensDoUsuario(id));
    }

    @ApiOperation("Listar todas as postagens do meu usuário")
    @GetMapping("/meuUsuario")
    public ResponseEntity<List<PostagemResponseDto>> buscarTodasPostagensDoMeuUsuario() {
        return ResponseEntity.ok(service.buscarPostagensDoUsuario(tokenUtil.getEmailUsuario()));
    }

    @ApiOperation("Listar todas as postagens de quem eu sigo.")
    @GetMapping("/listaPost")
    public ResponseEntity<List<PostagemResponseDto>> buscarPostagensDeUsuariosQueSigo() {
        return ResponseEntity.ok(service.buscarPostagensDeQuemSigo(tokenUtil.getEmailUsuario()));
    }

    @ApiOperation("Buscar postagem específica")
    @GetMapping("/{idPost}")
    public ResponseEntity<PostagemResponseDto> buscarPost(@ApiParam("Identificação da Postagem") @PathVariable("idPost") String id) {
        return ResponseEntity.ok(service.buscarPostPeloId(id));
    }

    @ApiOperation("Cadastrar uma nova postagem.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> cadastrarPostagem(@ApiParam(name = "Postagem", required = true, value = "Representação de uma postagem.") @RequestBody PostagemDto postagemDto) {
        String id = service.cadastrar(postagemDto, tokenUtil.getEmailUsuario());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation("Editar uma postagem.")
    @PutMapping("/{idPostagem}")
    @ResponseStatus(HttpStatus.ACCEPTED)

    public ResponseEntity<Void> editarPostagem(@ApiParam(name = "Postagem", required = true, value = "Representação de uma postagem.") @PathVariable("idPostagem") String id, @RequestBody PostagemDto postagemDto) {

        service.editar(id, postagemDto);
        return ResponseEntity.accepted().build();
    }

    @ApiOperation("Excluir uma postagem (apenas o criador pode excluir).")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> excluirPostagem(@ApiParam(required = true, value = "Id de identificação da postagem a ser excluida.") @PathVariable("id") String id){
        service.excluir(tokenUtil.getEmailUsuario(), id);
        return ResponseEntity.accepted().build();
    }

    @ApiOperation("Dar Like na postagem.")
    @PostMapping("/{id}/like")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> darLike(@ApiParam(required = true, value = "Id de identificação da postagem a receber like.") @PathVariable("id") String id){
        service.darLike(id, tokenUtil.getEmailUsuario());
        return ResponseEntity.accepted().build();
    }
    @ApiOperation("Remover Like da postagem.")
    @DeleteMapping("/{id}/like")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> removerLike(@ApiParam(required = true, value = "Id de identificação da postagem a remover like.") @PathVariable("id") String id){
        service.removerLike(id, tokenUtil.getEmailUsuario());
        return ResponseEntity.accepted().build();
    }

    @ApiOperation("Listar likes de uma postagem.")
    @GetMapping("/{id}/like")
    public ResponseEntity<List<UsuarioResponseDto>> buscarTodosLikesDeUmaPostagem(@ApiParam(required = true, value = "Identificação de postagem") @PathVariable("id") String idPostagem){
        List<UsuarioResponseDto> usuario = service.buscarTodosLikesDeUmPost(idPostagem);
        return ResponseEntity.ok(usuario);
    }
}
