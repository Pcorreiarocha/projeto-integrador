package com.faculdade.controllers;

import com.faculdade.commons.support.Response;
import com.faculdade.controllers.dtos.usuario.UsuarioRequestDto;
import com.faculdade.controllers.dtos.usuario.UsuarioResponseDto;
import com.faculdade.domain.entity.UsuarioEntity;
import com.faculdade.controllers.dtos.login.LoginRequestDto;
import com.faculdade.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Response<UsuarioResponseDto>> save(@RequestBody UsuarioRequestDto usuarioRequestDto ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(Response.<UsuarioResponseDto>builder()
                                           .code( 0 )
                                           .success( Boolean.TRUE )
                                           .message( "OK" )
                                           .data( this.usuarioService.save( usuarioRequestDto ) )
                                           .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> findById(@PathVariable Long id) {
        UsuarioEntity usuarioEntity = usuarioService.findById(id);
        if ( usuarioEntity != null) {
            return ResponseEntity.ok( usuarioEntity );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> update(@PathVariable Long id, @RequestBody UsuarioEntity usuarioEntityAtualizado ) {
        UsuarioEntity usuarioEntity = usuarioService.findById(id);
        if ( usuarioEntity != null) {
            usuarioEntityAtualizado.setId(id);
            return ResponseEntity.ok(usuarioService.save( usuarioEntityAtualizado ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        UsuarioEntity usuarioEntity = usuarioService.findById(id);
        if ( usuarioEntity != null) {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<UsuarioEntity>> findAll() {
        List< UsuarioEntity > usuarioEntities = usuarioService.findAll();
        return ResponseEntity.ok( usuarioEntities );
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioEntity> login( @RequestBody LoginRequestDto loginResponse ) {
        UsuarioEntity usuarioEntityAutenticado = usuarioService.authenticate( loginResponse.cpf(), loginResponse.senha() );
        if ( usuarioEntityAutenticado != null) {
            return ResponseEntity.ok( usuarioEntityAutenticado );
        } else {
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).build();
        }
    }
}