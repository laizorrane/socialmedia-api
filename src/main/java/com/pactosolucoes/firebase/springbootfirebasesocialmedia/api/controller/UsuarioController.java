package com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.controller;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.UsuarioService;
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
 * Data Criacao: 11/08/2021 - 14:15
 */
@Api(tags = "Usuários")
@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;


    @ApiOperation("Filtrar usuários")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> buscarUsuario(@ApiParam("Nome dos usuários.") @RequestParam("nome") String nome) {
        return ResponseEntity.ok(service.buscarTodosPorNome(nome));
    }

    @ApiOperation("Buscar usuário")
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDto> buscarUsuario(@ApiParam("Identificação do Usuário") @PathVariable("idUsuario") Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @ApiOperation("Editar usuário")
    @PutMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> editarUsuario(@ApiParam("Identificação do Usuário") @PathVariable("idUsuario") Long id,
                                                            @RequestBody UsuarioDto usuarioDto) {
        service.editar(id, usuarioDto);
        return ResponseEntity.accepted().build();
    }

    @ApiOperation("Cadastrar Usuário")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody UsuarioDto usuario) {
        Long id = service.cadastrar(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation("Excluir usuário")
    @DeleteMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> editarUsuario(@ApiParam("Identificação do Usuário") @PathVariable("idUsuario") Long id) {
        service.deletar(id);
        return ResponseEntity.accepted().build();
    }

}
