package com.faculdade.controllers;

import com.faculdade.commons.support.Response;
import com.faculdade.controllers.dtos.usuario.UsuarioRequestDto;
import com.faculdade.controllers.dtos.usuario.UsuarioResponseDto;
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
    public ResponseEntity<Response<UsuarioResponseDto>> save( @RequestBody UsuarioRequestDto usuarioRequestDto ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(Response.<UsuarioResponseDto>builder()
                                           .code( 0 )
                                           .success( Boolean.TRUE )
                                           .message( "OK" )
                                           .data( this.usuarioService.save( usuarioRequestDto ) )
                                           .build());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Response<UsuarioResponseDto>> findById( @PathVariable Long idUsuario ) {
        return ResponseEntity.ok( Response.<UsuarioResponseDto>builder()
                .code( 0 )
                .success( Boolean.TRUE )
                .message( "OK" )
                .data( this.usuarioService.findById( idUsuario ) )
                .build());
    }

    @DeleteMapping( "/{idUsuario}" )
    public ResponseEntity<Response<Void>> delete( @PathVariable Long idUsuario ) {
        this.usuarioService.delete( idUsuario );
        return ResponseEntity.accepted().body(
                Response.<Void>builder().code( 0 ).success( Boolean.TRUE ).message( "OK" ).build() );
    }

    @GetMapping("/")
    public ResponseEntity<Response<List<UsuarioResponseDto>>> findAll() {
        List<UsuarioResponseDto> usuarioResponseDtoList = usuarioService.findAll();
        return usuarioResponseDtoList.isEmpty() ? new ResponseEntity<>(
                Response.<List<UsuarioResponseDto>>builder()
                        .build(), HttpStatus.NO_CONTENT ) : ResponseEntity.ok( Response.<List<UsuarioResponseDto>>builder()
                                                                                       .code( 0 )
                                                                                       .success( Boolean.TRUE )
                                                                                       .message( "OK" )
                                                                                       .data( usuarioResponseDtoList )
                                                                                       .build() );
    }

    @PostMapping("/login")
    public ResponseEntity<Response<UsuarioResponseDto>> login( @RequestBody LoginRequestDto loginResponseDto ) {
        UsuarioResponseDto usuarioResponseDto = usuarioService.authenticate( loginResponseDto.cpf(), loginResponseDto.senha() );
        if ( usuarioResponseDto != null) {
            return ResponseEntity.ok( Response.<UsuarioResponseDto>builder()
                                              .code( 0 )
                                              .success( Boolean.TRUE )
                                              .message( "OK" )
                                              .data( usuarioResponseDto )
                                              .build());
        } else {
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).build();
        }
    }
}