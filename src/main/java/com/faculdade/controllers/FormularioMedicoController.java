package com.faculdade.controllers;

import com.faculdade.commons.support.Response;
import com.faculdade.controllers.dtos.formulario_medico.FormularioMedicoRequestDto;
import com.faculdade.controllers.dtos.formulario_medico.FormularioMedicoResponseDto;
import com.faculdade.services.FormularioMedicoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping( "/formulario" )
public class FormularioMedicoController {

    private final FormularioMedicoService formularioMedicoService;

    /*@GetMapping( "/{idFormulario}" )
    public ResponseEntity<Response<FormularioMedicoResponseDto>> findById( @PathVariable Long idFormulario ) {
        return ResponseEntity.ok( Response.<FormularioMedicoResponseDto>builder()
                                          .code( 0 )
                                          .success( Boolean.TRUE )
                                          .message( "OK" )
                                          .data( this.formularioMedicoService.findById( idFormulario ) )
                                          .build() );
    }*/

    @PostMapping
    public ResponseEntity<Response<FormularioMedicoResponseDto>> save( @RequestBody FormularioMedicoRequestDto formularioMedicoRequestDto ) {
        return ResponseEntity.status( HttpStatus.CREATED )
                             .body( Response.<FormularioMedicoResponseDto>builder()
                                            .code( 0 )
                                            .success( Boolean.TRUE )
                                            .message( "OK" )
                                            .data( this.formularioMedicoService.save( formularioMedicoRequestDto ) )
                                            .build() );
    }

    /*@GetMapping( "/" )
    public ResponseEntity<Response<List<FormularioMedicoResponseDto>>> findAll() {
        List<FormularioMedicoResponseDto> formularioMedicoResponseDtoList = formularioMedicoService.findAll();
        return formularioMedicoResponseDtoList.isEmpty() ? new ResponseEntity<>(
                Response.<List<FormularioMedicoResponseDto>>builder()
                        .build(), HttpStatus.NO_CONTENT ) : ResponseEntity.ok( Response.<List<FormularioMedicoResponseDto>>builder()
                                                                                       .code( 0 )
                                                                                       .success( Boolean.TRUE )
                                                                                       .message( "OK" )
                                                                                       .data( this.formularioMedicoService.findAll() )
                                                                                       .build() );
    }*/
}
